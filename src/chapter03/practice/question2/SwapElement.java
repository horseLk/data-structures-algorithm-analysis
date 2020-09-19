package chapter03.practice.question2;

import dataStruct.DoubleNode;
import dataStruct.SingleNode;

/**
 * 3.2 通过调整链来交换两个相邻元素，分别用单链表和双链表实现
 */
public class SwapElement {
    public SingleNode<Integer> swapBySingle(SingleNode<Integer> head) {
        SingleNode<Integer> pHead = new SingleNode<>(-1, head);
        SingleNode<Integer> pre = pHead;
        SingleNode<Integer> p = head;
        while (p != null && p.next != null) {
            SingleNode<Integer> next = p.next.next;
            pre.next = p.next;
            p.next = next;
            pre.next.next = p;
            pre = p;
            p = next;
        }
        return pHead.next;
    }

    public DoubleNode<Integer> swapByDouble(DoubleNode<Integer> head) {
        DoubleNode<Integer> pHead = new DoubleNode<>(-1, null, head);
        head.prev = pHead;
        DoubleNode<Integer> pre = pHead;
        DoubleNode<Integer> p = head;
        while (p != null && p.next != null) {
            DoubleNode<Integer> cur = p.next.next;

            pre.next = p.next;
            p.next = cur;
            pre.next.next = p;

            if (cur != null) {
                cur.prev = pre.next;
            }

            p.prev = pre.next;
            pre.next.prev = pre;

            pre = p;
            p = cur;
        }
        return pHead.next;
    }
}
