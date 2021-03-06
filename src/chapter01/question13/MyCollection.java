package chapter01.question13;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义的集合类
 * @param <T>
 */
public class MyCollection <T> {
    private List<T> list;

    public MyCollection() {
        list = new ArrayList<>();
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }

    public void makeEmpty() {
        list.clear();
    }

    public boolean insert(T t) {
        return list.add(t);
    }

    public boolean remove(T t) {
        return list.remove(t);
    }

    public boolean isPresent(T t) {
        return list.contains(t);
    }
}
