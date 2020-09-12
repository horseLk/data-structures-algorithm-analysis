package chapter1.question3;

/**
 * 1.3 使用书中的printDigit方法输出一个都变了类型的数字
 */
public class Solution {
    public void printOut(double n) {
        int leftPart = (int) n;
        printOutInt(leftPart);
        System.out.print(".");
        printOutDouble((n - (int)n) * 10);

    }

    // 因为十进制double类型的数字在二进制中不一定可以又穷表示，因此此处可能输出一个无限接近于输入数字的数。
    public void printOutDouble(double n) {
        if (n == 0) return;
        printOutInt((int) n);
        printOutDouble((n - (int)n) * 10);
    }

    public void printOutInt(int n) {
        if (n >= 10) {
            printOutInt(n / 10);
        }
        printDigit(n % 10);
    }

    public void printDigit(int n) {
        if (n / 10 > 0) {
            System.out.println("error");
        }
        System.out.print(n);
    }
}
