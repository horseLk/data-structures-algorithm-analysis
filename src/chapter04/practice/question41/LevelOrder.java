package chapter04.practice.question41;

import chapter04.practice.question33.DeleteLeaves;

import java.util.ArrayList;
import java.util.List;

/**
 * 层序遍历
 */
public class LevelOrder<T> {
    public static class Node<T> {
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

    public List<T> levelOrder(Node<T> root) {
        List<T> res = new ArrayList<>();
        if (root == null) return res;
        Node<T> node = root;
        res.add(node.element);
        List<Node<T>> temp = new ArrayList<>();
        temp.add(node);
        while (!temp.isEmpty()) {
            List<Node<T>> ttemp = new ArrayList<>();
            for (Node<T> p : temp) {
                if (p.left != null) {
                    res.add(p.left.element);
                    ttemp.add(p.left);
                }
                if (p.right != null) {
                    res.add(p.right.element);
                    ttemp.add(p.right);
                }
            }
            temp.clear();
            temp.addAll(ttemp);
        }
        return res;
    }
}
