package chapter03.practice.question4;

import java.util.ArrayList;
import java.util.List;

/**
 * 已知有两个排序集合
 * 3.4  求这两个集合的并集
 * 3.5  求这两个集合的交集
 */
public class UnionAndIntersection {
    public List<Integer> union(List<Integer> list1, List<Integer> list2) {
        List<Integer> res = new ArrayList<>();
        int m = list1.size();
        int n = list2.size();
        int i = 0, j = 0;
        while (i < m && j < n) {
            if (list1.get(i) == list2.get(j)) {
                res.add(list1.get(i));
                i++;j++;
            } else if (list1.get(i) < list2.get(j)) {
                res.add(list1.get(i));
                i++;
            } else {
                res.add(list2.get(j));
                j++;
            }
        }

        while (i < m) {
            res.add(list1.get(i));
            i++;
        }
        while (j < n) {
            res.add(list2.get(j));
            j++;
        }
        return res;
    }

    public List<Integer> intersection(List<Integer> list1, List<Integer> list2) {
        List<Integer> res = new ArrayList<>();
        int m = list1.size();
        int n = list2.size();
        int i = 0, j = 0;
        while (i < m && j < n) {
            if (list1.get(i) == list2.get(j)) {
                res.add(list1.get(i));
                i++;
                j++;
            } else if (list1.get(i) < list2.get(j)) {
                i++;
            } else {
                j++;
            }
        }
        return res;
    }
}
