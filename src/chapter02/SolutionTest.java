package chapter02;

import chapter02.example.example01.EuclidAlgorithm;
import chapter02.example.example02.EffictPow;
import chapter02.practice.question26.Solution;
import chapter02.practice.question27.FindNumber;

/**
 * 第二章的算法测试类
 */
public class SolutionTest {
    public static void main(String[] args) {
        // 欧几里算法求最大公因数测试
        System.out.println(new EuclidAlgorithm().gcd(1989, 1590));

        //高效求幂的测试
        EffictPow myPow = new EffictPow();
        double a = 7.5;
        int b = 23;
        System.out.println(myPow.pow1(a, b));
        System.out.println(myPow.pow2(a, b));
        System.out.println(Math.pow(a, b));

        // 主元素求解问题
        int nums[] = {3,3,4,2,4,4,2,4,4};
        Integer mainNum = new Solution().getMainElement(nums);
        if (mainNum == null) {
            System.out.println("no main number");
        } else {
            System.out.println(mainNum);
        }

        //  搜索二维矩阵
        int[][] matrix = {
                {1,   3,  5,  7},
        {10, 11, 16, 20},
            {23, 30, 34, 50},
                {24, 32, 43, 60}
        };
        System.out.println(new FindNumber().find(matrix, 24));
    }
}
