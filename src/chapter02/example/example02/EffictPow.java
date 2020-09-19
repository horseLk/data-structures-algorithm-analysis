package chapter02.example.example02;

public class EffictPow {
    public double pow1(double x, int n) {
        if (n == 0) return 1;
        if (n == 1) return x;
        if (n % 2 == 0) {
            return pow1(x * x, n / 2);
        } else {
            return pow1(x * x, n / 2) * x;
        }
    }

    public double pow2(double x, int n) {
        double res = 1.0;
        double temp = x;
        while (n != 0) {
            int cur = n & 1;
            if (cur == 1) {
                res *= temp;
            }
            temp *= temp;
            n = n >>> 1;
        }
        return res;
    }
}
