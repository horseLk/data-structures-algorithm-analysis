package chapter04.practice.question37;

import java.util.ArrayList;
import java.util.List;

public class RangeFind<T extends Comparable<? super T>> {
    private static class Node<T> {
        T element;
        Node<T> left;
        Node<T> right;

        public Node(T t) {
            this(t, null, null);
        }

        public Node(T t, Node<T> _left, Node<T> _right) {
            this.element = t;
            this.left = _left;
            this.right = _right;
        }
    }

    private Node<T> root;

    public List<T> rangeFind(T k1, T k2) {
        return rangeFind(new ArrayList<>(), k1, k2, root);
    }

    private List<T> rangeFind(List<T> res, T k1, T k2, Node<T> node) {
        if (node == null) return res;
        if (k1.compareTo(node.element) <= 0) { // k1 <= node.element，node继续往小的寻找扩大范围
            res.addAll(rangeFind(res, k1, k2, node.left));
        }
        if (k1.compareTo(node.element) <= 0 && k2.compareTo(node.element) >= 0) { // 在这个范围内
            res.add(node.element);
        }
        if (k2.compareTo(node.element) >= 0) { // k2 >= node.element， node继续往大的寻找扩大范围
            res.addAll(rangeFind(res, k1, k2, node.right));
        }
        return res;
    }
}
