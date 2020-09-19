package chapter03.example.example01;

import java.util.*;

/**
 * 一个自己实现的ArrayList集合
 */
public class MyArrayList<T> implements Iterable<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private T[] items;

    public MyArrayList() {
        doClear();
    }

    public void clear() {
        doClear();
    }

    private void doClear() {
        size = 0;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void trmToSize() {
        ensureCapacity(size);
    }

    public T get(int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return items[index];
    }

    public T set(int index, T newValue) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T oldValue = items[index];
        items[index] = newValue;
        return oldValue;
    }

    public void ensureCapacity(int newCapacity) {
        if (newCapacity < size) return;
        T[] oldItems = items;
        items = (T[]) (new Object[newCapacity]);
        for (int i = 0; i < size; i++) {
            items[i] = oldItems[i];
        }
    }

    public boolean add(T t) {
        add(size(), t);
        return true;
    }

    public void add (int index, T t) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (size == items.length) {
            ensureCapacity(size * 2 + 1); // 此处必须 + 1， 因为 size 有可能出现为 0 的情况
        }
        for (int i = size; i > index; i--) {
            items[i] = items[i - 1];
        }
        items[index] = t;
        size++;
    }

    public void addAll(Iterable<? extends T> items) {
        Iterator<? extends T> iterator = items.iterator();
        while (iterator.hasNext()) {
            T next = iterator.next();
            add(next);
        }
    }

    public T remove(int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T value = items[index];
        for (int i = index; i < size() - 1; i++) {
            items[i] = items[i + 1];
        }
//        items[size() - 1] = null;   --- 这一句可以不写，因为使用get始终不能访问
        size--;
        return value;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyArrayListIterator();
    }

    public ListIterator<T> listIterator() {
        return new MyArrayListListIterator();
    }

    private class MyArrayListIterator implements  Iterator<T>{

        private int current = 0; // current指向下一个元素坐标

        @Override
        public boolean hasNext() {
            return current < size();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return items[current++];
        }

        @Override
        public void remove() {
            MyArrayList.this.remove(--current); // 移除的是当前iterator指向的上一个元素
        }
    }

    private class MyArrayListListIterator implements ListIterator<T> {
        private int current = 0;

        @Override
        public boolean hasNext() {
            return current < MyArrayList.this.size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return items[current++];
        }

        @Override
        public boolean hasPrevious() {
            return current > 0;
        }

        @Override
        public T previous() {
            return items[--current];
        }

        @Override
        public int nextIndex() {
            return current;
        }

        @Override
        public int previousIndex() {
            return current - 1;
        }

        @Override
        public void remove() {
            if (current <= 0 || current > size()) {
                throw new ArrayIndexOutOfBoundsException();
            }
            MyArrayList.this.remove(--current);
        }

        @Override
        public void set(T t) {
            if (current <= 0 || current > size()) {
                throw new ArrayIndexOutOfBoundsException();
            }
            MyArrayList.this.set(current - 1, t);
        }

        @Override
        public void add(T t) {
            MyArrayList.this.add(current, t);
        }
    }
}
