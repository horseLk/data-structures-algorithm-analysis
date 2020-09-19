package chapter03.practice.question33;

/**
 * 使用循环数组实现队列类
 */
public class QueueByArray<T> {
    private T[] items;
    private int front;
    private int rear;
    private boolean isFull;

    public QueueByArray(int n) {
        if (n <= 10) n = 10;
        items = (T[])(new Object[n]);
        front = 0;
        rear = 0;
        isFull = false;
    }

    public void push(T t) {
        if (isFull) throw new IllegalStateException();
        int index = rear % items.length;
        items[index] = t;
        rear = index + 1;
        if (rear == front) isFull = true;
    }

    public T top() {
        return items[front];
    }

    public T pop() {
        T value = items[front];
        front = (front + 1) % items.length;
        isFull = false;
        return value;
    }

    public int size() {
        if (isFull) return items.length;
        int len = items.length;
        return (rear - front + len) % len;
    }
}
