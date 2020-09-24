package chapter06.example.example01;

import java.util.Arrays;

/**
 * 需求：找到第k小的元素
 * @param <T>
 */
public class KthNumber<T extends Comparable<? super T>> {
    public T findKthElement(T[] array, int k) {
        if (k > array.length) return null;
        BinaryHead<T> h = new BinaryHead(array, k);
        return h.findKthMinElement();
    }
}


class BinaryHead<K extends Comparable<? super K>> {
    private K[] items;
    private K[] overageItems; // 还没有使用的元素
    private int currentSize;
    private K kMinValue; // 目前第k小的值
    private int kMinValueIndex;

    public BinaryHead(K[] arr, int k) {
        currentSize = k;
        items = (K[]) new Comparable[k + 1];
        kMinValue = null;
        kMinValueIndex = -1;
        for (int i = 0; i < k; i++) {
            items[i + 1] = arr[i];
        }
        for (int i = k / 2; i > 0; i--) {
            percolateDown(i);
        }
        for (int i = currentSize / 2 + 1; i <= currentSize; i++) {
            if (kMinValue == null || items[i].compareTo(kMinValue) > 0) {
                kMinValue = items[i];
                kMinValueIndex = i;
            }
        }
        overageItems = (K[]) new Comparable[arr.length - k];
        for (int i = 0; i < overageItems.length; i++) {
            overageItems[i] = arr[k + i];
        }
        generate();
    }

    public void percolateDown(int hole) {
        int child;
        K cur = items[hole];
        while (hole * 2 <= currentSize) {
            child = hole * 2;
            if (child < currentSize && items[child].compareTo(items[child + 1]) > 0) {
                child++;
            }
            if (cur.compareTo(items[child]) > 0) {
                items[hole] = items[child];
                hole = child;
            } else break;
        }
        items[hole] = cur;
    }

    public void generate() {
        for (K item : overageItems) {
            if (item.compareTo(kMinValue) >= 0) {
                continue;
            }
            items[kMinValueIndex] = item;
            kMinValue = item;
            for (int i = currentSize / 2; i > 0; i--) {
                percolateDown(i);
            }
            for (int i = currentSize / 2 + 1; i <= currentSize ; i++) {
                if (items[i].compareTo(kMinValue) > 0) {
                    kMinValue = items[i];
                    kMinValueIndex = i;
                }
            }
        }
    }

    public K findKthMinElement() {
        return kMinValue;
    }
}
