package chapter03.practice.question28;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
    3.28 自己实现的双端队列
 */
public class MyDeque<T> {
    private List<T> list;

    public MyDeque () {
        list = new LinkedList<>();
    }

    public void push(T x) {
        list.add(0, x);
    }

    public T pop() {
        return list.remove(0);
    }

    public void inject(T x) {
        list.add(list.size(), x);
    }

    public T eject() {
        return list.remove(list.size() - 1);
    }
}
