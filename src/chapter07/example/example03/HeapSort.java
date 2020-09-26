package chapter07.example.example03;

/**
 * 堆排序
 */
public class HeapSort<T extends Comparable<? super T>> {
    public void heapSort(T[] arr) {
        int len = arr.length;
        for (int i = (len - 1 - 1) / 2; i >= 0 ; i--) {
            percolateDown(arr, i, len);
        }
        for (int i = 0; i < len; i++) {
            T last = arr[len - 1 - i];
            arr[len - 1 - i] = arr[0];
            arr[0] = last;
            percolateDown(arr, 0, len - i - 1);
        }
    }

    private void percolateDown(T[] array, int index, int lastIndex) {
        T cur = array[index];
        int child;
        for (; index * 2 + 1 < lastIndex; index = child) {
            child = index * 2 + 1;
            if (child + 1 < lastIndex && array[child].compareTo(array[child + 1]) < 0) {
                child++;
            }
            if (array[child].compareTo(cur) > 0) {
                array[index] = array[child];
            } else {
                break;
            }
        }
        array[index] = cur;
    }
}
