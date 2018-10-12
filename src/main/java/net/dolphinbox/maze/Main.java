package net.dolphinbox.maze;

import java.io.File;
import java.util.*;
import net.dolphinbox.maze.RMazeGen;

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
        Scanner scan = new Scanner(System.in);

        // Prints "Hello, World" to the terminal window.
        if(debugMessages) System.out.println("Hello, World");

        // Prompt the user for what option they want (Random Maze, File Input)
        System.out.println("Please select an option: 1 for random maze, 2 for File IO");
        int option = 0;
        while(true){
            option = Integer.parseInt(scan.nextLine());
            if(option == 1 | option == 2){
                break;
            } else {
                System.out.println("Please choose 1 or 2. ");
            }
        }
        if(option == 1){
            maze  = RMazeGen.randomMazeGen(10, 10);
        } else if(option == 2){
            System.out.println("Please enter a file name to read from...");
            String filename = scan.nextLine();
            maze = readMazeFromFile(filename);
        } else {
            System.out.println("Sorry, an unexpected eror has occured.");
        }
        int[][] startPoint = findStartPoint(maze, "S");
        if(debugMessages) System.out.println(startPoint[0][0] + ", " + startPoint[1][0]);

        computeMaze(startPoint[0][0], startPoint[1][0]);
        // We overwrote S, set it back before printing...
        maze[startPoint[0][0]][startPoint[1][0]] = "S";

        System.out.println("This is the solution: (F is the path): ");
        System.out.println(Arrays.deepToString(maze));
    }

    public static String[][] readMazeFromFile(String fileName){
        Scanner scan = new Scanner(System.in);
        File fileObj = new java.io.File(fileName);
        Scanner file = new Scanner(System.in);
        try{
            file = new Scanner(fileObj);
        } catch (Exception e){
            System.out.println("Error getting file... " + e);
        }

        // Get rows (hor)
        int hor = Integer.parseInt(file.nextLine());
        
        // Get columns (ver)
        int ver = Integer.parseInt(file.nextLine());
        
        // Get border block
        String border = file.nextLine();

        // Get path block
        String path = file.nextLine();

        // Get start block
        String start = file.nextLine();

        // Get exit block
        String exit = file.nextLine();

        String[][] inputMaze = new String[ver][hor]; // Create a maze array.
        if(debugMessages) System.out.println("Loading array...");
        for(int i = 0; i < ver; i++){
            String mazeLine = file.nextLine();
            if(debugMessages) System.out.println("Current Line of Array: " + mazeLine);
            for(int x = 0; x < hor; x++){
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
