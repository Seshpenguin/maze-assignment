package net.dolphinbox.maze;

import java.io.File;
import java.util.*;
import net.dolphinbox.maze.RMazeGen;

public class Main {

    public static boolean debugMessages = true;

    public static String[][] maze = { { "B", "B", "B", "B", "B", "B", "B", "B", "B", "B", "B" },
            { "B", "O", "O", "O", "B", "O", "B", "O", "O", "O", "B" },
            { "B", "O", "B", "O", "B", "O", "B", "O", "B", "B", "B" },
            { "B", "O", "B", "O", "S", "O", "O", "O", "O", "O", "B" },
            { "B", "O", "B", "B", "B", "B", "O", "B", "B", "O", "B" },
            { "B", "O", "B", "O", "O", "O", "O", "O", "O", "O", "B" },
            { "B", "B", "B", "B", "B", "B", "B", "X", "B", "B", "B" } };

    // Maze metadata (Set by user when using file IO)
    public static String borderBlock = "B";
    public static String pathBlock = "O";
    public static String startBlock = "S";
    public static String exitBlock = "X";

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // Prints "Hello, World" to the terminal window.
        if (debugMessages)
            System.out.println("Hello, World");

        // Prompt the user for what option they want (Random Maze, File Input)
        System.out.println("Please select an option: 1 for random maze, 2 for File IO");
        int option = 0;
        while (true) {
            option = Integer.parseInt(scan.nextLine());
            if (option == 1 | option == 2) {
                break;
            } else {
                System.out.println("Please choose 1 or 2. ");
            }
        }
        if (option == 1) {
            System.out.println("Please enter the vertical then horizontal size: ");
            int userVer = Integer.parseInt(scan.nextLine());
            int userHor = Integer.parseInt(scan.nextLine());
            maze = RMazeGen.randomMazeGen(userVer, userHor);
        } else if (option == 2) {
            System.out.println("Please enter a file name to read from...");
            String filename = scan.nextLine();
            maze = readMazeFromFile(filename);
        } else {
            System.out.println("Sorry, an unexpected eror has occured.");
        }
        int[][] startPoint = findStartPoint(maze, startBlock);
        if (debugMessages)
            System.out.println(startPoint[0][0] + ", " + startPoint[1][0]);

        boolean mazeResult = computeMaze(startPoint[0][0], startPoint[1][0]);
        
        // We overwrote S, set it back before printing...
        maze[startPoint[0][0]][startPoint[1][0]] = startBlock;

        if(mazeResult){
            System.out.println("This is the solution: (+ is the path): ");
            printMaze();
        } else {
            System.out.println("Sorry, this maze has no solution!");
        }

        
    }

    public static String[][] readMazeFromFile(String fileName) {
        Scanner scan = new Scanner(System.in);
        File fileObj = new java.io.File(fileName);
        Scanner file = new Scanner(System.in);
        try {
            file = new Scanner(fileObj);
        } catch (Exception e) {
            System.out.println("Error getting file... " + e);
        }

        // Get rows (ver)
        int ver = Integer.parseInt(file.nextLine());

        // Get columns (hor)
        int hor = Integer.parseInt(file.nextLine());

        // Get border block
        borderBlock = file.nextLine();

        // Get path block
        pathBlock = file.nextLine();

        // Get start block
        startBlock = file.nextLine();

        // Get exit block
        exitBlock = file.nextLine();

        String[][] inputMaze = new String[ver][hor]; // Create a maze array.
        if (debugMessages)
            System.out.println("Loading array...");
        for (int i = 0; i < ver; i++) {
            String mazeLine = file.nextLine();
            if (debugMessages)
                System.out.println("Current Line of Array: " + mazeLine);
            for (int x = 0; x < hor; x++) {
                inputMaze[i][x] = Character.toString(mazeLine.charAt(x));
            }
        }
        return inputMaze;
    }

    public static int[][] findStartPoint(String[][] maze, String item) {
        for (int ver = 0; ver < maze.length; ver++) {
            for (int hor = 0; hor < maze[ver].length; hor++) {
                if (maze[ver][hor].equals(item)) {
                    int[][] pos = { { ver }, { hor } };
                    if (debugMessages)
                        System.out.println("[Debug] THE START BLOCK IS: " + maze[ver][hor]);
                    return pos;
                }
            }
        }
        int[][] pos = { { 0 }, { 0 } };
        return pos;
    }

    public static boolean computeMaze(int ver, int hor) {
        if (maze[ver][hor].equals(exitBlock)) {
            if (debugMessages)
                System.out.println("[DEBUG] Found End Point @ " + ver + ", " + hor);
            //maze[ver][hor] = "+";
            return true;
        }

        if (maze[ver][hor].equals(borderBlock) || maze[ver][hor].equals("+")) {
            if (debugMessages)
                System.out.println("[DEBUG] Found Block/used path Point @ " + ver + ", " + hor);
            return false;
        }

        // Start step logic
        // If it is not at the final node, set the current node to F
        maze[ver][hor] = "+";

        if (ver+1 < maze.length) { // only if we are not at the edge (prevent array out of bounds)
            // go down
            if (computeMaze(ver + 1, hor))
                return true; // if this chain found the finish, return true (go back up)
        }

        if (ver-1 > 0) {
            // go up
            if (computeMaze(ver - 1, hor))
                return true;
        }

        if (hor-1 > 0) {
            // go left
            if (computeMaze(ver, hor - 1))
                return true;
        }

        if (hor+1 < maze[0].length) {
            // go right
            if (computeMaze(ver, hor + 1))
                return true;
        }

        // Nodes < 2 (Hit dead end), reset this path node.
        maze[ver][hor] = pathBlock;

        return false;
    }

    public static void printMaze(){
        for (int i = 0; i < maze.length; i++) {
            for (int x = 0; x < maze[0].length; x++) {
                System.out.print (maze[i][x] + " ");
            }
            System.out.println();
        }
    }

}
