package chapter1.question14;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * 自定义排序集和类
 * @param <T>
 */
public class OrderedCollection <T> {
    private Set<T> collection;
    private Comparator comparator;

    public OrderedCollection(Comparator<T> com) {
        comparator = com;
        collection = new TreeSet<>(com);
    }

    public boolean isEmpty() {
        return collection.size() == 0;
    }

    public void makeEmpty() {
        collection.clear();
    }

    public boolean insert(T t) {
        return collection.add(t);
    }

    public boolean remove(T t) {
        return  collection.remove(t);
    }

    public T findMax() {
        if (isEmpty()) return null;
        return (T) collection.stream().max(comparator).get();
    }

    public T findMin() {
        if (isEmpty()) return null;
        return (T) collection.stream().min(comparator).get();
    }
}
