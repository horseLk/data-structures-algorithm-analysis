package chapter07.example.example04;

public class MergeSort<T extends Comparable<? super T>> {
    public void mergeSort(T[] arr) {
        T[] temp = (T[]) new Comparable[arr.length];
        mergeSort(arr, temp, 0, arr.length - 1);
    }

    private void mergeSort(T[] arr, T[] temp, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, temp, left, mid);
            mergeSort(arr, temp, mid + 1, right);
            merge(arr, temp, left, mid + 1, right);
        }
    }

    private void merge(T[] arr, T[] temp, int left, int mid, int right) {
        int leftEnd = mid - 1;
        int tempPos = left;
        int numElement = right - left + 1;

        while (left <= leftEnd && mid <= right) {
            if (arr[left].compareTo(arr[mid]) <= 0) {
                temp[tempPos++] = arr[left++];
            } else {
                temp[tempPos++] = arr[mid++];
            }
        }
        while (left <= leftEnd) {
            temp[tempPos++] = arr[left++];
        }
        while (mid <= right) {
            temp[tempPos++] = arr[mid++];
        }

        for (int i = 0; i < numElement; i++, right--) {
            arr[right] = temp[right];
        }
    }
}
