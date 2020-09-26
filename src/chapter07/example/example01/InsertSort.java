package chapter07.example.example01;

/**
 * 插入排序
 */
public class InsertSort {

    public <T extends Comparable<? super T>> void insertSort(T[] arr) {
        for (int i = 1; i < arr.length; i++) {
            T cur = arr[i];
            int j;
            for (j = i; j > 0 && arr[j - 1].compareTo(cur) > 0; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = cur;
        }
    }
}
