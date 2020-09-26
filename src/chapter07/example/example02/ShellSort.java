package chapter07.example.example02;

/**
 * 希尔排序
 */
public class ShellSort {
    public <T extends Comparable<? super T>> void shellSort(T[] arr) {
        int h = arr.length / 2;
        while (h != 0) {
            for (int i = h; i < arr.length; i++) { // 每次从第 h 个元素开始
                T tmp = arr[i];
                int j;
                for (j = i; j >= h && tmp.compareTo(arr[j - h]) < 0; j -= h)
                    arr[j] = arr[j - h];
                arr[j] = tmp;
            }
            h = h / 2;
        }
    }
}
