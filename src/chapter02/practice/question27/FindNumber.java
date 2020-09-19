package chapter02.practice.question27;

/**
 * 一个n x n数组，每一行从左到右递增，每一行从上到下递增，给出一个O(n)的最坏情形算法找到目标数字
 */
public class FindNumber {
    public boolean find(int[][] matrix, int target) {
        int n = matrix.length;
        int x = 0;
        int y = n - 1;
        while (isArea(x, y, n)) {
            if (matrix[x][y] == target) return true;
            else if (matrix[x][y] > target) {
                y--;
            }else {
                x++;
            }
        }
        return false;
    }

    private boolean isArea(int x, int y, int n) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }
}
