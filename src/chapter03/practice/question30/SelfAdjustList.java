package chapter03.practice.question30;

/**
 * 3.30 自调整表  数组实现
 */
public class SelfAdjustList<T> {
    private T[] items;
    private int size;
    private int length;

    public SelfAdjustList(int n) {
        items = (T[]) new Object[n];
        size = 0;
        length = n;
    }

    public void add(T t) {
        if (size == length) {
            throw new IndexOutOfBoundsException();
        }
        for (int i = size; i > 0; i--) {
            items[i] = items[i - 1];
        }
        items[0] = t;
        size++;
    }

    public T find(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        T value = items[index];
        while (index > 0){
            items[index] = items[index - 1];
            index--;
        }
        items[index] = value;
        return value;
    }
}
