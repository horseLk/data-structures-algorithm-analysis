package chapter09.example.example03;

public class Floyd {
    public int[][] floyd(int[][] M) {
        int len = M.length;
        int mxValue = 1000;
        int[][] res = new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                res[i][j] = M[i][j];
            }
        }

        for (int k = 0; k < len; k++) {
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < len; j++) {
                    res[i][j] = Math.min(res[i][j], res[i][k] + res[k][j]);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int IMAX = 1000;
        int[][] M = {{0, 2, IMAX, 1, IMAX, IMAX, IMAX},
                {IMAX, 0, IMAX, 3, 10, IMAX, IMAX},
                {4, IMAX, 0, IMAX, IMAX, 5, IMAX},
                {IMAX, IMAX, 2, 0, 2, 8, 4},
                {IMAX, IMAX, IMAX, IMAX, 0, IMAX, 6},
                {IMAX, IMAX, IMAX, IMAX, IMAX, 0, IMAX},
                {IMAX, IMAX, IMAX, IMAX, IMAX, 1, 0}};

        int[][] res = new Floyd().floyd(M);
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res.length; j++) {
                System.out.print(res[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
