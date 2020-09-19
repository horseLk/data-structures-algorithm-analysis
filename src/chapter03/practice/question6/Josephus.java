package chapter03.practice.question6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 3.6 Josephus问题
 */
public class Josephus {
    public int josephus(int m, int n) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(i + 1);
        }
        int count = 0; // 已经被清除人数
        int cur = 0; // 目前拿着热土豆的人，初始是第一个
        int curLen = n;
        while (count != n -1) {
            int del = (cur + m) % (curLen--);
            list.remove(del);
            count++;
            cur = del;
        }
        return list.get(0);
    }
}
