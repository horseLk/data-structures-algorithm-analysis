package chapter08.practice.question9;

import java.util.Random;

/**
 * 使用并查集生成一个迷宫
 */
public class GenerateMaze {
    public int[][] generateMaze(int m, int n) {
        int[][] maze = new int[m][n];
        int[] status = new int[m * n];
        for (int i = 0; i < m * n; ){
            status[i++] = -1;
        }
        int count = m * n;
        Random r = new Random();
        while (count != 1) {
            int a = r.nextInt(m * n);
            int b = r.nextInt(m * n);
            if (a == b || (find(status, a) == find(status, b) && find(status, a) != -1)) continue;
            if (b - a == 1 || b - a == -1 || b - a == 5 || b - a == -5) {
                int a_x = a / m;
                int a_y = a - a_x * m;
                maze[a_x][a_y] = 1;
                int b_x = b / m;
                int b_y = b - b_x * m;
                maze[b_x][b_y] = 1;
                union(status, a, b);
                count -= 2;
            }
        }
        return maze;
    }

    private void union(int[] status, int a, int b) {
        int index1 = find(status, a);
        int index2 = find(status, b);

        if (status[index1] < status[index2]) {
            status[index2] = index1;
        } else {
            if (status[index1] == status[index2]) {
                status[index2]--;
            }
            status[index1] = index2;
        }
    }

    private int find(int[] status, int x) {
        if (status[x] < 0) {
            return x;
        } else {
            status[x] = find(status, status[x]);
            return status[x];
        }
    }
}
