package chapter03;

import chapter03.example.example01.MyArrayList;
import chapter03.practice.question2.SwapElement;
import chapter03.practice.question22.AfterCalc;
import chapter03.practice.question23.TransformMidAndAfter;
import chapter03.practice.question4.UnionAndIntersection;
import chapter03.practice.question6.Josephus;
import dataStruct.DoubleNode;
import dataStruct.SingleNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class SolutionTest {
    public static void main(String[] args) {
        // 3.2 单链表测试
//        SingleNode<Integer> tail = new SingleNode<>(2);
//        SingleNode<Integer> node1 = new SingleNode<>(3, tail);
//        SingleNode<Integer> node2 = new SingleNode<>(8, node1);
//        SingleNode<Integer> node3 = new SingleNode<>(6, node2);
//        SingleNode<Integer> node4 = new SingleNode<>(9, node3);
//        SingleNode<Integer> node5 = new SingleNode<>(7, node4);
//        SingleNode<Integer> node6 = new SingleNode<>(1, node5);
//        SingleNode<Integer> node7 = new SingleNode<>(5, node6);
//        node7.print();
//        SingleNode<Integer> newNode = new SwapElement().swapBySingle(node7);
//        newNode.print();


        // 3.2 多链表测试
//        DoubleNode<Integer> node1 = new DoubleNode<>(2, null, null);
//        DoubleNode<Integer> node2 = new DoubleNode<>(3, null, node1);
//        DoubleNode<Integer> node3 = new DoubleNode<>(8, null, node2);
//        DoubleNode<Integer> node4 = new DoubleNode<>(6, null, node3);
//        DoubleNode<Integer> node5 = new DoubleNode<>(9, null, node4);
//        DoubleNode<Integer> node6 = new DoubleNode<>(7, null, node5);
//        DoubleNode<Integer> node7 = new DoubleNode<>(1, null, node6);
//        DoubleNode<Integer> node8 = new DoubleNode<>(5, null, node7);
//        node1.prev = node2;
//        node2.prev = node3;
//        node3.prev = node4;
//        node4.prev = node5;
//        node5.prev = node6;
//        node6.prev = node7;
//        node7.prev = node8;
//        node8.print();
//        DoubleNode<Integer> newNode = new SwapElement().swapByDouble(node8);
//        newNode.print();

//        List<Integer> list1 = new ArrayList<>();
//        list1.add(1);
//        list1.add(4);
//        list1.add(5);
//        list1.add(6);
//        list1.add(7);
//
//        List<Integer> list2 = new ArrayList<>();
//        list2.add(1);
//        list2.add(4);
//        list2.add(6);
//        list2.add(7);
//        list2.add(9);
//        // 3.4 两个排序表求并集测试
//        System.out.println("union: " + new UnionAndIntersection().union(list1, list2));
//        // 3.5 两个表求交集测试
//        System.out.println("intersection: " + new UnionAndIntersection().intersection(list1, list2));

        // 3.6 josephus 问题测试
//        System.out.println(new Josephus().josephus(4, 5));

        // 3.9 MyArrayList 的 addAll 测试
//        MyArrayList<Integer> list1 = new MyArrayList<>();
//        List<Integer> list2 = new ArrayList<>();
//        list2.add(1);
//        list2.add(2);
//        list2.add(5);
//        list1.addAll(list2);
//        Iterator<Integer> iterator = list1.iterator();
//        while (iterator.hasNext()) {
//            System.out.print(iterator.next() + " ");
//        }

        // 3.22 后缀表达式求值测试
//        System.out.println(new AfterCalc().calc("6523+8*+3+*"));

        //3.23 中缀表达式转后缀测试
        String afterVal = new TransformMidAndAfter().midToAfter("a+b*c+(d*e+f)*g");
        System.out.println(afterVal);
        String midVal = new TransformMidAndAfter().afterToMid(afterVal);
        System.out.println(midVal);
    }
}
