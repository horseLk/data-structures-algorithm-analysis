package chapter04.practice.question36;

import chapter04.practice.question33.DeleteLeaves;

import java.util.ArrayList;
import java.util.List;

public class BuildAVL<T extends Comparable<? super T>> {
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

    private Node<T> root;

    public BuildAVL(int h){
        buildAVL(h);
    }

    public void buildAVL(int h) {
        Node<T> _root = new Node(1);
        int cur = 2;
        List<Node<T>> pre = new ArrayList<>();
        pre.add(_root);

        List<Node<T>> temp = new ArrayList<>();
        for (int curH = 1; curH <=h ; curH++) {
            for (Node<T> node : pre) {
                node.left = new Node(cur++);
                node.right = new Node(cur++);
                temp.add(node.left);
                temp.add(node.right);
            }
            pre.clear();
            pre.addAll(temp);
            temp.clear();
        }
        root = _root;
    }
}
