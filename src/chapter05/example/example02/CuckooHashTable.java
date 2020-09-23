package chapter05.example.example02;

import java.util.Random;

interface HashFamily<Z> { // 用于指定一个散列函数
    int hash(Z x, int which);
    int getNumberOfFunctions();
    void generateNewFunctions();
}

public class CuckooHashTable<T> {
    private static final double MAX_LOAD = 0.4;
    private static final int ALLOWED_REHASHES = 1;
    private static final int DEFAULT_TABLE_SIZE = 16;

    private final HashFamily<? super T> hashFunctions;
    private final int numHashFunctions;
    private T[] array;
    private int currentSize;

    public CuckooHashTable(HashFamily<? super T> hashFunctions) {
        this(hashFunctions, DEFAULT_TABLE_SIZE);
    }

    public CuckooHashTable(HashFamily<? super T> hf, int size) {
        allocateArray(size);
        doClear();
        hashFunctions = hf;
        numHashFunctions = hf.getNumberOfFunctions();
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public void makeEmpty() {
        doClear();
    }

    public boolean contains(T x) {
        return findPos(x) != -1;
    }

    public boolean remove(T x) {
        int pos = findPos(x);
        if (pos != -1) {
            array[pos] = null;
            currentSize--;
        }
        return pos != -1;
    }

    public boolean insert(T x) {
        if (contains(x)) return false;
        if (currentSize >= array.length * MAX_LOAD) {
            expand();
        }
        return insertHelper(x);
    }

    private void allocateArray(int size) {
        array = (T[]) new Object[size];
    }

    private void doClear() {
        currentSize = 0;
        for (int i = 0; i < array.length; i++) {
            array[i] = null;
        }
    }

    private int myHash(T x, int which) {
        int hashVal = hashFunctions.hash(x, which);
        hashVal %= array.length;
        if (hashVal < 0)
            hashVal += array.length;
        return hashVal;
    }

    private int findPos(T x) {
        for (int i = 0; i < numHashFunctions; i++) {
            int pos = myHash(x, i);
            if (array[pos] != null && array[pos] == x) {
                return pos;
            }
        }
        return -1;
    }

    private void expand() {
        reHash((int) (array.length / MAX_LOAD));
    }

    private void reHash() {
        hashFunctions.generateNewFunctions();
        reHash(array.length);
    }

    private void reHash(int newLength) {
        T[] oldArr = array;
        allocateArray(newLength);
        doClear();
        currentSize = 0;
        for (T x : oldArr) {
            insert(x);
        }
    }

    private int rehashes = 0;
    private Random r = new Random();
    private boolean insertHelper(T x) {
        final int COUNT_LIMIT = 100;
        while (true) {
            int lastPos = -1;
            int pos;

            for (int count = 0; count < COUNT_LIMIT; count++) {
                for (int i = 0; i < numHashFunctions; i++) {
                    pos = myHash(x, i);
                    if (array[pos] == null) {
                        array[pos] = x;
                        currentSize++;
                        return true;
                    }
                }
                int i = 0;
                do {
                    pos = myHash(x, r.nextInt(numHashFunctions));
                } while (pos == lastPos && i++ < 5);

                T tmp = array[lastPos = pos];
                array[pos] = x;
                x = tmp;
            }

            if (++rehashes > ALLOWED_REHASHES) {
                expand();
                rehashes = 0;
            } else {
                reHash();
            }
        }
    }
}
