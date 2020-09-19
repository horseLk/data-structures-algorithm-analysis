package chapter01.question6;

import java.util.ArrayList;
import java.util.List;

/**
 * 1.6 输出一个字符串的所有排列
 */
public class Solution {
    StringBuilder builder = new StringBuilder();
    List<Integer> indexes = new ArrayList<>();
    public void permute(String str){
        char[] chs = str.toCharArray();
        permute(chs, 0, chs.length);
    }

    public void permute(char[] str, int low, int high) {
        if (builder.length() == high) {
            System.out.println(builder.toString());
            return;
        }

        for (int i = 0; i < high; i++) {
            if (indexes.contains(i)) continue;
            builder.append(str[i]);
            indexes.add(i);
            permute(str, i + 1, high);
            builder.deleteCharAt(builder.length() - 1);
            indexes.remove(new Integer(i));
        }
    }
}
