package chapter08;

import chapter08.practice.question9.GenerateMaze;

public class SolutionTest {
    public static void main(String[] args) {
        int[][] maze = new GenerateMaze().generateMaze(5, 5);
        for (int[] row : maze) {
            for (int x : row) {
                System.out.print(x + " ");
            }
            System.out.println();
        }
    }
}
