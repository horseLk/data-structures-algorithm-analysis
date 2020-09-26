package chapter07;

import chapter07.example.example07.SelectSort;
import chapter07.example.example08.BucketSort;
import chapter07.example.example09.RadixSort;
import chapter07.practice.question14.ReHeapSort;

import java.util.Arrays;

public class SolutionTest {
    public static void main(String[] args) {
        Integer[] arr = {6,3,9,2,5,4,1,7,8,0};
        String[] strs = {"horse", "liaok", "zxcvb", "qwert", "abcde", "acbds"};
        // 插入排序
//        new InsertSort().insertSort(arr);

        // 希尔排序
//        new ShellSort().shellSort(arr);

        // 堆排序
//        new HeapSort<Integer>().heapSort(arr);

        // 归并排序
//        new MergeSort<Integer>().mergeSort(arr);

        // 快速排序
//        new QuickSort<Integer>().quickSort(arr);

        // 冒泡排序
//        new BubbleSort().bubbleSort(arr);

        // 选择排序
//        new SelectSort().selectSort(arr);

        // 桶排序
//        new BucketSort().bucketSort(arr);

        // 基数排序
//        new RadixSort().radixSortA(strs, 5);

        // 计数基数排序
//        new RadixSort().countingRadixSort(strs, 5);

        // 7.14 测试
        new ReHeapSort<Integer>().heapSort(arr, 3, 7);
        System.out.println(Arrays.toString(arr));
//        System.out.println(Arrays.toString(strs));
    }
}
