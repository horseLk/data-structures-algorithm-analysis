package chapter06.example.practice.question10;

import chapter06.example.example01.BinaryHeap;

import java.util.List;

/**
 * 找出二叉堆中小于某个值k 的所有节点
 * @param <T>
 */
public class FindElements<T extends Comparable<? super T>> {
    public List<T> findElements(BinaryHeap<T> heap, T k) {
        return heap.findMin(k);
    }
}
