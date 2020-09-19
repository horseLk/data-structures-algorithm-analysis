package chapter03.practice.question31;

import java.util.NoSuchElementException;

/**
 * 单链表实现的栈类
 * @param <T>
 */
public class StackByLinkedList<T> {
    public static class Node<T> {
        public T val;
        public Node<T> next;

        public Node(T t, Node<T> node) {
            val = t;
            next = node;
        }
    }

    private int size;
    private Node<T> header;

    public StackByLinkedList() {
        size = 0;
        header = null;
    }

    public StackByLinkedList(Node<T> node) {
        header = node;
        size = 0;
        while (node != null) {
            size++;
        }
    }

    public void push(T t) {
        Node<T> newNode = new Node<>(t, header);
        header = newNode;
        size++;
    }

    public T top() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return header.val;
    }

    public T pop() {
        if (size == 0)
            throw new NoSuchElementException();
        Node<T> node = header;
        header = header.next;
        size--;
        return node.val;
    }

}
