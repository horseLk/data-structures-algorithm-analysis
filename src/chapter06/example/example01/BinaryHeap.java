package chapter06.example.example01;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class BinaryHeap<T extends Comparable<? super T>> {
    private static final int DEFAULT_CAPACITY = 10;
    private int currentSize;
    private T[] items;

    public BinaryHeap() {
        this(DEFAULT_CAPACITY);
    }

    public BinaryHeap(int size) {
        allocate(size + 1);
        doClear();
    }

    public BinaryHeap(T[] array) {
        allocate((array.length + 2) * 11 / 10);
        doClear();
        currentSize = array.length;
        int i = 1;
        for (T item : array) {
            items[i++] = item;
        }
        buildHeap();
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public void makeEmpty() {
        doClear();
    }

    public void insert(T x) {
        if (currentSize == items.length - 1) {
            enlarge(items.length * 2 + 1);
        }
        int hole = ++currentSize;
        // 此处设置 items[0] = x 是为了防止数组越界，
        // 这样的话就算是到了根节点也不会出现越界的情况，因为 x 会和 items[0] 比较结果为0，退出循环
        for (items[0] = x; x.compareTo(items[hole / 2]) < 0; hole /= 2) {
            items[hole] = items[hole / 2];
        }
        items[hole] = x;
    }

    public T findMin() {
        if (isEmpty()) throw new NoSuchElementException();
        return items[1];
    }

    public List<T> findMin(T t) {
        List<T> res = new ArrayList<>();
        if (findMin().compareTo(t) >= 0) {
            return res;
        }
        List<Integer> indexes = new ArrayList<>();
        indexes.add(1);
        while (!indexes.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
            for (int index : indexes) {
                res.add(items[index]);
                int child = index * 2;
                if (child <= currentSize && items[child].compareTo(t) < 0) {
                    temp.add(child++);
                }
                if (child <= currentSize && items[child].compareTo(t) < 0) {
                    temp.add(child);
                }
            }
            indexes.clear();
            indexes.addAll(temp);
        }
        return res;
    }

    public T deleteMin() {
        T value = findMin();
        items[1] = items[currentSize--];
        percolateDown(1);
        return value;
    }

    private void percolateDown(int hole) {
        int child;
        T cur = items[hole];
        for (; hole * 2 <= currentSize; hole = child) {
            child = hole * 2;
            if (child != currentSize && items[child + 1].compareTo(items[child]) < 0) {
                child++;
            }
            if (items[child].compareTo(cur) < 0) {
                items[hole] = items[child];
            } else {
                break;
            }
        }
        items[hole] = cur;
    }

    private void enlarge(int size) {
        T[] oldArr = items;
        allocate(size);
        doClear();
        for (int i = 1; i < oldArr.length; i++) {
            items[i] = oldArr[i];
        }
        currentSize = oldArr.length - 1;
    }

    private void buildHeap() {
        int lastNonLeaf = currentSize / 2;
        for (int i = lastNonLeaf; i > 0; i--) {
            percolateDown(i);
        }
    }

    private void doClear() {
        for (int i = 1; i < currentSize; i++) {
            items[i] = null;
        }
        currentSize = 0;
    }

    private void allocate(int size) {
        items = (T[]) new Comparable[size];
    }
}
