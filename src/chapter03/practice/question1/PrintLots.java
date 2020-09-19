package chapter03.practice.question1;

import java.util.List;

/**
 * 3.1 两个表中均是升序排序的数组，printLots(L, P)打印 L 中那些 P 中元素所指定位置上的元素
 */
public class PrintLots {
    public void printLots(List<Integer> L, List<Integer> P) {
        for (int index : P) {
            System.out.println(L.get(index));
        }
    }
}
