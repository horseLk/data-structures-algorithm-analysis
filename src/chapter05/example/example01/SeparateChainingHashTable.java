package chapter05.example.example01;

import java.util.LinkedList;
import java.util.List;

/**
 * 分离链接法实现的哈希表
 * @param <T>
 */
public class SeparateChainingHashTable<T> {
    private static final int DEFAULT_TABLE_SIZE = 16;
    private List<T>[] theLists;
    private int currentSize;

    public SeparateChainingHashTable() {
        this(DEFAULT_TABLE_SIZE);
    }

    public SeparateChainingHashTable(int size) {
        theLists = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            theLists[i] = new LinkedList<>();
        }
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public void makeEmpty() {
        for (int i = 0; i < theLists.length; i++) {
            theLists[i].clear();
        }
        currentSize = 0;
    }

    public boolean contains(T x) {
        List<T> list = theLists[myHash(x)];
        return list.contains(x);
    }

    public void insert(T x) {
        List<T> list = theLists[myHash(x)];
        if (!list.contains(x)) {
            list.add(x);

            if (++currentSize > theLists.length) {
                reHash();
            }
        }
    }

    public void remove(T x) {
        List<T> list = theLists[myHash(x)];
        if (list.contains(x)) {
            list.remove(x);
            currentSize--;
        }
    }

    private int myHash(T x) {
        int hash = x.hashCode();
        hash %= theLists.length;
        if (hash < 0) {
            hash += theLists.length;
        }
        return hash;
    }

    private void reHash() {
        List<T>[] oldLists = theLists;
        theLists = new LinkedList[theLists.length * 2];
        currentSize = 0;
        for (int i = 0; i < oldLists.length; i++) {
            for (T x : oldLists[i]) {
                insert(x);
            }
        }
    }
}
