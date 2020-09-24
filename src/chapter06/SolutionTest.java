package chapter06;

import chapter06.example.example01.BinaryHeap;
import chapter06.example.example01.KthNumber;
import chapter06.example.practice.question12.BuildHeap;

public class SolutionTest {
    public static void main(String[] args) {
//        KthNumber<Integer> h = new KthNumber<>();
//        Integer arr[] = {2, 3, 5, 4, 1, 9, 11};
//        System.out.println(h.findKthElement(arr, 6));

        // 6.12 测试
        BinaryHeap<Integer> integerBinaryHeap = new BuildHeap().buildHeap();
    }
}
