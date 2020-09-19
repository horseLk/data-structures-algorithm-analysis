package chapter03.example.example02;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T> implements Iterable<T> {
    private static class Node<T> { // 链表节点类
        public T data;
        public Node<T> prev;
        public Node<T> next;

        public Node(T t, Node<T> p, Node<T> n) {
            data = t;
            prev = p;
            next = n;
        }
    }

    private int size; // 链表大小
    private int modCount; // 构造以来对链表所作改变的次数
    private Node<T> beginMaker; // 头节点
    private Node<T> endMaker; // 尾节点

    public void clear() {
        doClear();
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean add(T t) {
        add(size(), t);
        return true;
    }

    public void add(int index, T t) {
        // size() 是指向尾节点的，但是尾节点中是没有任何数据的。因为可能插入到尾节点之前
        addBefore(getNode(index, 0, size()), t);
    }

    public T get(int index) {
        return getNode(index).data;
    }

    private void addBefore(Node<T> node, T t) {
        Node<T> newNode = new Node<>(t, node.prev, node);
        node.prev.next = newNode;
        node.prev = newNode;
        size++;
        modCount++;
    }

    public boolean contains(T t) {
        return indexOf(t) != -1;
    }

    public int indexOf(T t) {
        int index = 0;
        Node node = beginMaker.next;
        while (node != null) {
            if (node.data.equals(t)) {
                return index;
            }
            node = node.next;
            index++;
        }
        return -1;
    }

    public T set(int index, T newValue) {
        Node<T> node = getNode(index);
        T oldValue = node.data;
        node.data = newValue;
        return oldValue;
    }

    public T remove(int index) {
        return remove(getNode(index));
    }

    public void removeAll(Iterable<? extends T> items) {
        Iterator<? extends T> iterator = items.iterator();
        while (iterator.hasNext()) {
            T next = iterator.next();
            int index = -1;
            if ((index = indexOf(next)) != -1) {
                remove(index);
            }
        }
    }

    public void addFirst(T t) {
        add(0, t);
    }

    public void addLast(T t) {
        add(size(), t);
    }

    public T removeFirst() {
        Node<T> node = beginMaker.next;
        remove(node);
        return node.data;
    }

    public T removeLast() {
        Node<T> node = endMaker.prev;
        remove(node);
        return node.data;
    }

    public T getFirst() {
        return beginMaker.next.data;
    }

    public T getLast() {
        return endMaker.prev.data;
    }

    private Node<T> getNode(int index) {
        return getNode(index, 0, size() - 1);
    }

    private Node<T> getNode(int index, int lower, int upper) {
        Node<T> node;
        if (index < lower || index > upper) {
            throw new IndexOutOfBoundsException();
        }
        if (index < size() / 2) {
            node = beginMaker.next;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else {
            node = endMaker;
            for (int i = size(); i > index; i--) {
                node = node.prev;
            }
        }
        return node;
    }

    private T remove(Node<T> node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
        size--;
        modCount++;
        return node.data;
    }

    public void doClear() {
        // 设置一个空的头尾节点
        beginMaker = new Node<T>(null, null, null);
        endMaker = new Node<T>(null, beginMaker, null);
        beginMaker.next = endMaker;
        size = 0;
        modCount++;
    }

    public void printLinkedList() {
        StringBuilder builder = new StringBuilder();
        Node<T> node = endMaker.prev;
        while (node != beginMaker) {
            builder.append(node.data + "<->");
            node = node.prev;
        }
        if (builder.length() == 0) {
            System.out.println("empty LinkedList");
            return;
        }
        System.out.println(builder.delete(builder.length() - 3, builder.length()).toString());
    }

    @Override
    public Iterator<T> iterator() {
        return new MyLinkedListIterator();
    }

    public ListIterator<T> listIterator() {
        return new MyLinkedListListIterator();
    }

    private class MyLinkedListIterator implements Iterator<T> {
        private Node<T> current = beginMaker.next;
        private int expectedModCount = modCount; // 进入迭代器之前的操作次数
        private boolean okToRemove = false;

        @Override
        public boolean hasNext() {
            return current != endMaker;
        }

        @Override
        public T next() {
            if (modCount != expectedModCount) { // 每次操作前必须保证迭代器的操作次数和链表的操作次数一致。避免多并发问题
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T nextItem = current.data;
            current = current.next;
            okToRemove = true; // 表示当前元素的前一个还没有被移除，是可以被移除的
            return nextItem;
        }

        @Override
        public void remove() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            if (!okToRemove) {
                throw new IllegalStateException();
            }
            MyLinkedList.this.remove(current.prev);
            expectedModCount++;
            okToRemove = false; // 表示当前元素的前一个已经被移除了，不能继续remove了
        }
    }

    private class MyLinkedListListIterator implements ListIterator<T> {
        private int index = 0;
        private Node<T> current = beginMaker.next;
        private int expectedModCount = modCount; // 进入迭代器之前的操作次数
        private boolean okToRemove = false;

        @Override
        public boolean hasNext() {
            return current != endMaker;
        }

        @Override
        public T next() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T data = current.data;
            current = current.next;
            okToRemove = true;
            index++;
            return data;
        }

        @Override
        public boolean hasPrevious() {
            return current.prev != beginMaker;
        }

        @Override
        public T previous() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            current = current.prev;
            T data = current.data;
            okToRemove = true;
            index--;
            return data;
        }

        @Override
        public int nextIndex() {
            return index;
        }

        @Override
        public int previousIndex() {
            return index - 1;
        }

        @Override
        public void remove() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            if (!okToRemove) {
                throw new IllegalStateException();
            }
            MyLinkedList.this.remove(current.prev);
            expectedModCount++;
            okToRemove = false; // 表示当前元素的前一个已经被移除了，不能继续remove了
        }

        @Override
        public void set(T t) {
            MyLinkedList.this.set(index, t);
        }

        @Override
        public void add(T t) {
            MyLinkedList.this.add(index++, t);
            expectedModCount++;

        }
    }
}
