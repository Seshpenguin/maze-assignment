package net.dolphinbox.maze;

import java.lang.reflect.Method;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Prints "Hello, World" to the terminal window.
        System.out.println("Hello, World");

        String[][] maze = { { "B", "B", "B", "B", "B", "B", "B", "B", "B", "B", "B" },
                { "B", "O", "O", "O", "B", "O", "B", "O", "O", "O", "B" },
                { "B", "O", "B", "O", "B", "O", "B", "O", "B", "B", "B" },
                { "B", "O", "B", "O", "S", "O", "O", "O", "O", "O", "B" },
                { "B", "O", "B", "B", "B", "B", "O", "B", "B", "O", "B" },
                { "B", "O", "B", "O", "O", "O", "O", "O", "O", "O", "B" },
                { "B", "B", "B", "B", "B", "B", "B", "X", "B", "B", "B" } };

        boolean[][] tried = new boolean[7][11];

        int[][] startPoint = findStartPoint(maze, "S");
        System.out.println(startPoint[0][0] + ", " + startPoint[1][0]);

        setBIsFalse(maze, tried, "B");

        computeMaze(maze, tried, startPoint);

        System.out.println(Arrays.deepToString(tried));

    }

    public static int[][] findStartPoint(String[][] maze, String item) {
        for (int y = 0; y < maze.length; y++) {
            for (int x = 0; x < maze[y].length; x++) {
                if (maze[y][x].equals(item)) {
                    int[][] pos = { { x }, { y } };
                    return pos;
                }
            }
        }
        int[][] pos = { { 0 }, { 0 } };
        return pos;
    }

    public static void setArrayTrue(boolean[][] tried) {
        for (int y = 0; y < tried.length; y++) {
            for (int x = 0; x < tried[y].length; x++) {
                tried[y][x] = true;
            }
        }
    }

    public static void setBIsFalse(String[][] maze, boolean[][] tried, String barrierChar) {
        for (int y = 0; y < maze.length; y++) {
            for (int x = 0; x < maze[y].length; x++) {
                if (maze[y][x].equals(barrierChar)) {
                    tried[y][x] = true;
                }
            }
        }
    }

    public static boolean computeMaze(String[][] maze, boolean[][] tried, int[][] startPoint) {
        int x = startPoint[0][0];
        int y = startPoint[1][0];

        System.out.println("Current Position: " + x + ", " + y);

        int numNodes = 0;

        if (tried[y + 1][x]) { // if it's true UP
            int[][] startPointA = { { x }, { y + 1 } };
            numNodes++;
            if(!computeMaze(maze, tried, startPointA)){
                tried[y + 1][x] = false;
                return false;
            }
        }
        if (tried[y - 1][x]) { // if it's true DOWM
            int[][] startPointA = { { x }, { y - 1 } };
            numNodes++;
            if(!computeMaze(maze, tried, startPointA)){
                tried[y - 1][x] = false;
                return false;
            }
        }
        if (tried[y][x + 1]) { // if it's true RIGHT
            int[][] startPointA = { { x }, { y + 1 } };
            numNodes++;
            if(!computeMaze(maze, tried, startPointA)){
                tried[y][x + 1] = false;
                return false;
            }
        }
        if (tried[y][x - 1]) { // if it's true LEFT
            int[][] startPointA = { { x }, { y + 1 } };
            numNodes++;
            if(!computeMaze(maze, tried, startPointA)){
                tried[y][x - 1] = false;
                return false;
            }
        }

        if (numNodes < 2) {
            tried[y][x] = false;
            return false;
        }

        return true;
    }
}
