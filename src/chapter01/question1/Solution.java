package chapter01.question1;

/**
 * 1.1 需求：解决一个选择问题。令k = N / 2.
 * 选择问题：找出数组中第k大的数字
 */
public class Solution {
    public int findKNumber(int[] nums, int k) {
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = nums[i];
        }
        mySort(res);
        for(int i = k; i < nums.length; i++) {
            if(nums[i] > res[k - 1]) {
                insert(res, nums[i], k);
            }
        }
        return res[k - 1];
    }

    private void insert(int[] res, int num, int k) {
        for (int i = 0; i < k; i++) {
            if (res[i] >= num) continue;
            int j = k - 1;
            for (; j > i ; j--) {
                res[j] = res[j - 1];
            }
            res[j] = num;
            break;
        }
    }

    private void mySort(int[] res) {
        int m = res.length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m - i - 1; j++) {
                if (res[j] < res[j + 1]) {
                    int temp = res[j];
                    res[j] = res[j + 1];
                    res[j + 1] = temp;
                }
            }
        }
    }
}
