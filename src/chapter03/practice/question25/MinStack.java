package chapter03.practice.question25;

import java.util.ArrayList;
import java.util.List;

/**
 * 3.25 MinStack的实现
 */
public class MinStack {
    List<Integer> stack;
    List<Integer> min;

    public MinStack() {
        stack = new ArrayList<>();
        min = new ArrayList<>();
    }

    public void push (int i) {
        stack.add(i);
        if (min.size() == 0 || min.get(min.size() - 1) > i) {
            min.add(i);
        }
    }

    public int pop() {
        int value = stack.remove(stack.size() - 1);
        if (value == min.get(min.size() - 1)) {
            min.remove(min.size() - 1);
        }
        return value;
    }

    public int findMin() {
        return min.get(min.size() - 1);
    }
}
