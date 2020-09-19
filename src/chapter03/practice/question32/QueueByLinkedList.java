package chapter03.practice.question32;

import java.util.NoSuchElementException;

/**
 * 3.32 单链表实现的队列
 */
public class QueueByLinkedList<T> {
    public class Node<T> {
        public T val;
        public Node<T> next;

        public Node(T t, Node<T> node) {
            val = t;
            next = node;
        }
    }
    private int size;
    private Node<T> header;
    private Node<T> tail;

    public QueueByLinkedList() {
        size = 0;
        header = null;
        tail = null;
    }

    public QueueByLinkedList(Node<T> node) {
        size = 1;
        header = node;
        tail = node;
    }

    public void push(T t) {
        Node<T> newNode = new Node<>(t, null);
        if (tail == null) {
            tail = newNode;
            header = newNode;
            return;
        }
        tail.next = newNode;
        size++;
    }

    public T top() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return header.val;
    }

    public T pop() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        Node<T> node = header;
        header = header.next;
        if (tail.next == header) { // 只有一个元素的情况
            tail = header;
        }
        size--;
        return node.val;
    }
}
