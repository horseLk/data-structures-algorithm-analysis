package chapter03.practice.question34;

/**
 * 3.34 循环链表
 */
public class LoopLinkedList<T> {
    public static class Node<T> {
        public T val;
        public Node<T> next;
    }
    public boolean isCycle(Node<T> head) {
        Node<T> fast = head;
        Node<T> slow = head;

        while (fast != null && slow != null) {
            if (fast == slow) {
                return true;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }
}
