package chapter07.example.example08;

public class BucketSort {
    public void bucketSort(Integer[] arr) {
        int[] buckets = new int[10];
        for (int i : arr) {
            buckets[i] += 1;
        }
        int index = 0;
        for (int i = 0; i < 10; i++) {
            if (buckets[i] == 0) continue;
            for (int j = buckets[i]; j > 0; j--) {
                arr[index++] = i;
            }
        }
    }
}
