package net.dolphinbox.maze;
import java.util.*;

public class Main {

    public static boolean debugMessages = true;

    public static String[][] maze = {
                { "B", "B", "B", "B", "B", "B", "B", "B", "B", "B", "B" },
                { "B", "O", "O", "O", "B", "O", "B", "O", "O", "O", "B" },
                { "B", "O", "B", "O", "B", "O", "B", "O", "B", "B", "B" },
                { "B", "O", "B", "O", "S", "O", "O", "O", "O", "O", "B" },
                { "B", "O", "B", "B", "B", "B", "O", "B", "B", "O", "B" },
                { "B", "O", "B", "O", "O", "O", "O", "O", "O", "O", "B" },
                { "B", "B", "B", "B", "B", "B", "B", "X", "B", "B", "B" } };



    public static void main(String[] args) {

        // Prints "Hello, World" to the terminal window.
        if(debugMessages) System.out.println("Hello, World");

        int[][] startPoint = findStartPoint(maze, "S");
        if(debugMessages) System.out.println(startPoint[0][0] + ", " + startPoint[1][0]);

        computeMaze(startPoint[0][0], startPoint[1][0]);
        // We overwrote S, set it back before printing...
        maze[startPoint[0][0]][startPoint[1][0]]

        System.out.println("This is the solution: (F is the path): ");
        System.out.println(Arrays.deepToString(maze));
    }

    public static int[][] findStartPoint(String[][] maze, String item) {
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
            if(debugMessages) System.out.println("[DEBUG] Found End Point @ " + ver + ", " + hor);
            return true;
        }

        if(maze[ver][hor].equals("B") || maze[ver][hor].equals("-")){
            if(debugMessages) System.out.println("[DEBUG] Found Block/used path Point @ " + ver + ", " + hor);
            return false;
        }

        // Start step logic
        //If it is not at the final node, set the current node to F
        maze[ver][hor] = "-";

        // go up
        if(computeMaze(ver + 1, hor)) return true; // if this chain found the finish, return true (go back up)

        // go down
        if(computeMaze(ver - 1, hor)) return true;

        // go left
        if(computeMaze(ver, hor - 1)) return true;

        // go right
        if(computeMaze(ver, hor + 1)) return true;

        // Nodes < 2 (Hit dead end), reset this path node.
        maze[ver][hor] = "O";

        return false;
    }

}
