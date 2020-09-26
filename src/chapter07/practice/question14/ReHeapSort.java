package chapter07.practice.question14;

/**
 * 7.14 重写堆排序，实现只排序指定范围的数据
 */
public class ReHeapSort<T extends Comparable<? super T>> {
    public void heapSort(T[] arr, int low, int high) {
        int len = high - low + 1;
        for (int i = len / 2 + low - 1; i >= low ; i--) {
            perDown(arr, low, i, high);
        }

        for (int i = 0; i < len; i++) {
            T last = arr[high - i];
            arr[high - i] = arr[low];
            arr[low] = last;
            perDown(arr, low, low, high - i - 1);
        }
    }

    private void perDown(T[] arr, int low, int index, int high) {
        T cur = arr[index];
        int child;
        for ( ;index * 2 - low + 1 <= high; index = child) {
            child = index * 2 - low + 1;
            if (child + 1 <= high && arr[child].compareTo(arr[child + 1]) < 0) {
                child++;
            }
            if (cur.compareTo(arr[child]) < 0) {
                arr[index] = arr[child];
            } else {
                break;
            }
        }
        arr[index] = cur;
    }
}
