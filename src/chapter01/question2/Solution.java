package chapter01.question2;

/**
 * 1.2 编写一个解字迷的游戏，判断是否可以在一个字谜表中找到一个单词
 */
public class Solution {
    public boolean findWords(char[][] board, String words) {
        int m = board.length;
        int n = board[0].length;
        int[][] directs = {{1, 0}, {1, 1}, {0, 1}, {-1, 1}, {-1, 0}, {-1, -1}, {0, -1}, {1, -1}};
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != words.charAt(0)) continue;
                for (int k = 0; k < 8; k++) {
                    if (judge(board, i, j, directs[k], words)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean judge(char[][] board, int i, int j, int[] direct, String words) {
        int offX = direct[0];
        int offY = direct[1];
        int len = words.length();
        int k = 0;
        while (inArea(i, j, board) && k < len) {
            if (board[i][j] != words.charAt(k)) {
                return false;
            }
            i += offX;
            j += offY;
            k++;
        }
        if (k == len) return true;
        return false;
    }

    private boolean inArea(int i, int j, char[][] board) {
        if (i >= 0 && i < board.length && j >= 0 && j < board[0].length) return true;
        return false;
    }
}
