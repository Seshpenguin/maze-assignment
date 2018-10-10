package net.dolphinbox.maze;

import java.util.Arrays;

public class Main {

    public static String[][] maze = {
                { "B", "B", "B", "B", "B", "B", "B", "B", "B", "B", "B" },
                { "B", "O", "O", "O", "B", "O", "B", "O", "O", "O", "B" },
                { "B", "O", "B", "O", "B", "O", "B", "O", "B", "B", "B" },
                { "B", "O", "B", "O", "S", "O", "O", "O", "O", "O", "B" },
                { "B", "O", "B", "B", "B", "B", "O", "B", "B", "O", "B" },
                { "B", "O", "B", "O", "O", "O", "O", "O", "O", "O", "B" },
                { "B", "B", "B", "B", "B", "B", "B", "X", "B", "B", "B" } };

    private static boolean debugMessages = true;

    public static void main(String[] args) {

        // Prints "Hello, World" to the terminal window.
        if(debugMessages) System.out.println("Hello, World");

        int[][] startPoint = findStartPoint(maze, "S");
        if(debugMessages) System.out.println(startPoint[0][0] + ", " + startPoint[1][0]);

        computeMaze(startPoint[0][0], startPoint[1][0]);
    }

    private static int[][] findStartPoint(String[][] maze, String item) {
        for (int ver = 0; ver < maze.length; ver++) {
            for (int hor = 0; hor < maze[ver].length; hor++) {
                if (maze[ver][hor].equals(item)) {
                    int[][] pos = { { ver }, { hor } };
                    if(debugMessages) System.out.println("[Debug] THE START BLOCK IS: " + maze[ver][hor]);
                    return pos;
                }
            }
        }
        int[][] pos = { { 0 }, { 0 } };
        return pos;
    }

    public static boolean computeMaze(int ver, int hor) {

        if (maze[ver][hor].equals("X")){
            if(debugMessages) System.out.print("[DEBUG] Found End Point @ " + ver + ", " + hor);
            return true;
        }

        if(maze[ver][hor].equals("B") || maze[ver][hor].equals("F")){
            if(debugMessages) System.out.print("[DEBUG] Found End Point @ " + ver + ", " + hor);
            return false;
        }

        // Start step logic
        boolean result;
        maze[ver][hor] = "F";

        //
        result = computeMaze(ver, hor);

        return false;
    }

}
