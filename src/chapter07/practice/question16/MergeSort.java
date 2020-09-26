package chapter07.practice.question16;

/**
 * 7.16 不使用递归的归并排序
 */
public class MergeSort<T extends Comparable<? super T>>  {
    public void mergeSort(T[] arr) {
        T[] temp = (T[]) new Comparable[arr.length];
        mergeSort(arr, temp, 0, arr.length - 1);
    }

    private void mergeSort(T[] arr, T[] temp, int left, int right) {
        int n = arr.length;
        for (int subSlitSize = 1; subSlitSize < n; subSlitSize *= 2) {
            int leftStart = 0;
            while (leftStart + subSlitSize < n) {
                int rightStart = leftStart + subSlitSize;
                int rightEnd = Math.min(n - 1, rightStart + subSlitSize - 1);

                merge(arr, temp, leftStart, rightStart, rightEnd);
            }
        }
    }

    private void merge(T[] arr, T[] temp, int leftStart, int rightStart, int rightEnd) {
        int leftEnd = rightStart - 1;
        int tempPos = leftStart;
        int elementCount = rightEnd - leftStart + 1;
        while (leftStart <= leftEnd && rightStart <= rightEnd) {
            if (arr[leftStart].compareTo(arr[rightStart]) < 0) {
                temp[tempPos++] = arr[leftStart++];
            } else {

                temp[tempPos++] = arr[rightStart++];
            }
        }
        while (leftStart <= rightStart) {
            temp[tempPos++] = arr[leftStart++];
        }
        while (rightStart <= rightEnd) {
            temp[tempPos++] = arr[rightStart++];
        }
        // 将合并后的数组写回到arr
        for (int i = 0; i < elementCount; rightEnd--, i++) {
            arr[rightEnd] = temp[rightEnd];
        }
    }
}
