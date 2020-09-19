package chapter03.practice.question11;

import java.util.NoSuchElementException;

/**
 * 3.11 单链表实现
 * @param <T>
 */
public class SingleList<T> {
    public static class SingleNode<T> {
        public T data;
        public SingleNode<T> next;

        public SingleNode(T _t, SingleNode<T> _n) {
            data = _t;
            next = _n;
        }
    }

    private int size;
    private SingleNode<T> beginMaker;

    public int size() {
        return this.size;
    }

    public void printLinkedList() {
        SingleNode<T> p = beginMaker.next;
        StringBuilder builder = new StringBuilder();
        while (p != null) {
            builder.append(p.data + "->");
            p = p.next;
        }
        if (builder.length() == 0) {
            throw new NullPointerException();
        }
        System.out.println(builder.delete(builder.length() - 2, builder.length()).toString());
    }

    public boolean contains(T x) {
        return indexOf(x) > -1;
    }

    private int indexOf(T x) {
        SingleNode<T> p = beginMaker.next;
        int index = 0;
        while (p != null) {
            if (p.data.equals(x))
                return index;
            p = p.next;
        }
        return -1;
    }

    public int notContainsAndInsert(T x) {
        if (contains(x)) {
            return indexOf(x);
        }
        SingleNode<T> node = new SingleNode<>(x, null);
        SingleNode<T> p = beginMaker;
        while (p.next != null) {
            p = p.next;
        }
        p.next = node;
        return size++;
    }

    public void remove(T x) {
        if (!contains(x)) {
            throw new NoSuchElementException();
        }

        SingleNode<T> p = beginMaker;
        while (p.next != null) {
            if (p.next.data == x) {
                p.next = p.next.next;
                break;
            }
        }
    }
}
