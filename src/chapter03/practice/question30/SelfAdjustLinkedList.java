package chapter03.practice.question30;

import java.util.LinkedList;
import java.util.List;

/**
 * 3.30 自调整表  链表实现
 */
public class SelfAdjustLinkedList<T> {
    private List<T> list;
    private int size;

    public SelfAdjustLinkedList() {
        list = new LinkedList<>();
        size = 0;
    }

    public void add(T t) {
        list.add(0, t);
        size++;
    }

    public T find(int index) {
        T value = list.remove(index);
        list.add(0, value);
        return value;
    }
}
