package chapter02.example.example01;

/**
 * 书中 2-10 提出的欧几里得算法求最大公因数(gcd)
 *      定理：两个整数的最大公约数等于其中较小的那个数和两数相除余数的最大公约数。
 */
public class EuclidAlgorithm {
    public int gcd(int m, int n) {
        if (m > n) {
            int temp = n;
            n = m;
            m = temp;
        }
        while (n != 0) {
            int rem = m % n;
            m = n;
            n = rem;
        }
        return m;
    }
}
