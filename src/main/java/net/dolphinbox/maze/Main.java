package net.dolphinbox.maze;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Prints "Hello, World" to the terminal window.
        System.out.println("Hello, World");

        String[][] maze = {
                { "B", "B", "B", "B", "B", "B", "B", "B", "B", "B", "B" },
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

        System.out.println(Arrays.deepToString(tried));

        computeMaze(maze, tried, startPoint);

        System.out.println(Arrays.deepToString(tried));

    }

    public static void converArrayToLinkList (String[][] maze, ) {

    }

    public static int[][] findStartPoint(String[][] maze, String item) {
        for (int ver = 0; ver < maze.length; ver++) {
            for (int hor = 0; hor < maze[ver].length; hor++) {
                if (maze[ver][hor].equals(item)) {
                    int[][] pos = { { ver }, { hor } };
                    System.out.println("THE START BLOCK IS: " + maze[ver][hor]);
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
        int ver = startPoint[0][0];
        int hor = startPoint[1][0];

        //System.out.println("Called method: " + Arrays.deepToString(startPoint));

        System.out.println("Current Position: " + ver + ", " + hor);

        int numNodes = 0;
        if (ver >= maze.length - 1 || hor >= maze[0].length - 1) { // if we hit a wall
            System.out.println("CANNOT TRAVSERSE WALL: " + ver + " " + hor);
            return false;
        }
        if(maze[ver][hor].equals("X")){
            return true;
        };
        
        // check the up and down from the current node.
        int[][] startPointA = {
            {ver + 1}, // up 1
            {hor}
        }; System.out.println("A");
        if(tried[ver + 1][hor]){
            numNodes++;
            System.out.println("IN A");
            if(computeMaze(maze, tried, startPointA)){
                System.out.println("UP 1 is false @ " + ver + " " + hor);
                tried[ver][hor] = false;
                return false;
            }
        }
        // Check one down
        int[][] startPointB = {
            {ver - 1}, // down 1
            {hor}
        }; System.out.println("B");
        if(tried[ver - 1][hor]){
            numNodes++;
            System.out.println("IN B");
            if(computeMaze(maze, tried, startPointB)){
                System.out.println("DOWN 1 is false @ " + ver + " " + hor);
                tried[ver][hor] = false;
                return false;
            }
        }

        // Check one right
        int[][] startPointC = {
            {ver}, // left 1
            {hor + 1}
        }; System.out.println("C");
        if(tried[ver][hor + 1]){
            numNodes++;
            System.out.println("IN C");
            if(computeMaze(maze, tried, startPointC)){
                System.out.println("RIGHT 1 is false @ " + ver + " " + hor);
                tried[ver][hor] = false;
                return false;
            }
        }

        // Check one left
        int[][] startPointD = {
            {ver}, // up 1
            {hor - 1}
        }; System.out.println("D");
        if(tried[ver][hor - 1]){
            numNodes++;
            System.out.println("IN D");
            if(computeMaze(maze, tried, startPointD)){
                System.out.println("LEFT 1 is false @ " + ver + " " + hor);
                tried[ver][hor] = false;
                return false;
            }
        }

        if(numNodes < 2){
            return false;
        }

        return false;
        
    }

    public static void computeMazeDFS(L node){

    }
}
