package net.dolphinbox.maze;

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

        setArrayTrue(tried);

        setBIsFalse(maze, tried, "B");

        computeMaze(maze, tried, startPoint);

        System.out.println(Arrays.deepToString(tried));

    }

    public static int[][] findStartPoint(String[][] maze, String item) {
        for (int ver = 0; ver < maze.length; ver++) {
            for (int hor = 0; hor < maze[ver].length; hor++) {
                if (maze[ver][hor].equals(item)) {
                    int[][] pos = { { hor }, { ver } };
                    return pos;
                }
            }
        }
        int[][] pos = { { 0 }, { 0 } };
        return pos;
    }

    public static void setArrayTrue(boolean[][] tried) {
        for (int ver = 0; ver < tried.length; ver++) {
            for (int hor = 0; hor < tried[ver].length; hor++) {
                tried[ver][hor] = true;
            }
        }
    }

    public static void setBIsFalse(String[][] maze, boolean[][] tried, String barrierChar) {
        for (int ver = 0; ver < maze.length; ver++) {
            for (int hor = 0; hor < maze[ver].length; hor++) {
                if (maze[ver][hor].equals(barrierChar)) {
                    tried[ver][hor] = false;
                }
            }
        }
    }

    public static boolean computeMaze(String[][] maze, boolean[][] tried, int[][] startPoint) {
        int hor = startPoint[0][0];
        int ver = startPoint[1][0];

        System.out.println("Called method: " + Arrays.deepToString(startPoint));

        System.out.println("Current Position: " + hor + ", " + ver);

        int numNodes = 0;
        if (ver >= maze.length - 1 || hor >= maze[0].length - 1) {
            return false;
        }
        
        // check the up and down from the current node.
        int[][] startPointA = {
            {ver},
            {hor}
        };
        if(computeMaze(maze, tried, startPoint)){
            
        }

        return false;
        
    }
}
