package chapter07.example.example09;

import java.util.ArrayList;
import java.util.List;

/**
 * 基数排序
 */
public class RadixSort {
    /*
        字符串基数排序
     */
    public void radixSortA(String[] arr, int strLen) {
        final int BUCKETS = 256;
        List<String>[] buckets = new ArrayList[BUCKETS]; // 用于保存每一轮放进去的字符串

        for (int i = 0; i < BUCKETS; i++) {
            buckets[i] = new ArrayList<>(); // 初始化
        }

        for (int pos = strLen - 1; pos >= 0; pos--) {
            for (String s : arr) {
                buckets[s.charAt(pos)].add(s); // 将arr中的所有数据装入各个桶中
            }

            int index = 0;
            for (List<String> thisBucket : buckets) { // 将桶中的数据更新到数组arr中
                for (String s : thisBucket) {
                    arr[index++] = s;
                }
                thisBucket.clear(); // 更新完毕后要清空桶
            }
        }
    }

    /*
        计数基数排序
     */
    public void countingRadixSort(String[] arr, int strLen) {
        final int BUCKETS = 256;
        int N = arr.length;
        String[] buffer = new String[N];
        String[] in = arr;
        String[] out = buffer;

        for (int pos = strLen - 1; pos >= 0; pos--) {
            int[] count = new int[BUCKETS + 1];
            for (int i = 0; i < N; i++) {
                count[in[i].charAt(pos)] += 1;
            }
            for (int b = 1; b <= BUCKETS; b++) {
                count[b] += count[b - 1];
            }
            for (int i = 0; i < N; i++) {
                out[count[in[i].charAt(pos)]++] = in[i];
            }
            String tmp[] = in;
            in = out;
            out = tmp;
        }

        if (strLen % 2 == 1) {
            for (int i = 0; i < arr.length; i++) {
                out[i] = in[i];
            }
        }
    }
}
