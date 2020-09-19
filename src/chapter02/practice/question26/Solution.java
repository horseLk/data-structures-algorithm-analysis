package chapter02.practice.question26;

import java.util.ArrayList;
import java.util.List;

/**
 * 26.求主元素
 */
public class Solution {
    public Integer getMainElement(int[] nums) {
        int len = nums.length;
        if (len == 0) return null;
        if (len == 1) return nums[0];
        int n = (len + 1) / 2;
        List<Integer> temp = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (2 * i + 1 >= len) {
                temp.add(nums[2 * i]);
                break;
            }
            if (nums[2 * i] == nums[2 * i + 1]) {
                temp.add(nums[2 * i]);
            }
        }
        int tempNums[] = new int[temp.size()];
        for (int i = 0; i < tempNums.length; i++) {
            tempNums[i] = temp.get(i);
        }
        return getMainElement(tempNums);
    }
}
