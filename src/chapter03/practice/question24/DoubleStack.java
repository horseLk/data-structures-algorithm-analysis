package chapter03.practice.question24;

import java.util.NoSuchElementException;

/**
 * 3.24 一个保存了两个栈的数组，数组的头和尾分别做两个栈的栈底。
 * @param <T>
 */
public class DoubleStack<T> {
    private T[] items;
    private int left; // 左栈顶
    private int right; // 右栈顶
    private int length;

    public DoubleStack (int n) {
        items = (T[])(new Object[n]);
        left = 0;
        right = n - 1;
        length = n;
    }

    public boolean isFull() {
        return right < left;
    }

    public int emptyElem() {
        return right - left + 1;
    }

    public boolean isEmptyInLeft() {
        return left == 0;
    }

    public int leftSize() {
        return left;
    }

    public T leftPop() {
        if (isEmptyInLeft()) {
            throw new NoSuchElementException();
        }
        T value = items[left - 1];
        items[--left] = null;
        return value;
    }

    public T leftTop() {
        if (isEmptyInLeft()) {
            throw new NoSuchElementException();
        }
        return items[left - 1];
    }

    public void leftPush(T t) {
        if (isFull()) {
            throw new IllegalStateException();
        }
        items[left++] = t;
    }

    public boolean isEmptyInRight() {
        return right == length - 1;
    }

    public int rightSize() {
        return length - right - 1;
    }

    public T rightPop() {
        if (isEmptyInRight()) {
            throw new NoSuchElementException();
        }
        T value = items[right + 1];
        items[++right] = null;
        return value;
    }

    public T rightTop() {
        if (isEmptyInRight()) {
            throw new NoSuchElementException();
        }
        return items[right + 1];
    }

    public void rightPush(T t) {
        if (isFull()) {
            throw new IllegalStateException();
        }
        items[right--] = t;
    }
}
