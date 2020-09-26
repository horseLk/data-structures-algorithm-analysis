package chapter07.example.example05;

public class QuickSort<T extends Comparable<? super T>> {
    public void quickSort(T[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private void quickSort(T[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int _left = left;
        int _right = right;
        T cur = arr[left];
        while (left < right) {
            while (left < right && arr[right].compareTo(cur) >= 0){
                right--;
            }
            if (left < right) {
                arr[left] = arr[right];
            }
            while (left < right && arr[left].compareTo(cur) <= 0) {
                left++;
            }
            if (left < right) {
                arr[right] = arr[left];
            }
        }
        arr[left] = cur;
        quickSort(arr, _left, left - 1);
        quickSort(arr, left + 1, _right);
    }
}
