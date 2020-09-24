package chapter06.example.practice.question12;

import chapter06.example.example01.BinaryHeap;

import java.util.Scanner;

/**
 * 输入 N 个元素并将他们插入到一个堆中
 */
public class BuildHeap {
    public BinaryHeap<Integer> buildHeap() {
        Scanner sc = new Scanner(System.in);
        BinaryHeap heap = new BinaryHeap();
        while (sc.hasNextInt()) {
            int x = sc.nextInt();
            heap.insert(x);
        }
        return heap;
    }
}
