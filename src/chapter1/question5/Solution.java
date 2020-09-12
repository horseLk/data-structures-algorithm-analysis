package chapter1.question5;

/**
 * 1.5 利用递归求出一个数的二进制表示中 1 的位数
 * 如果是奇数，其最低位为 1，那么这个数除以 2 之后的 1 的位数一定少一位，所以有 num(N) = num(N/2) + 1
 * 如果是偶数，最后一位一定是 0，那么这个数除以 2 之后，其 1 的位数不会变
 */
public class Solution {
    public int oneCount(int n) {
        if (n == 0) return 0;
        if (n % 2 == 0) {
            return oneCount(n / 2);
        } else {
            return oneCount(n / 2) + 1;
        }
    }
}
