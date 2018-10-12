package net.dolphinbox.maze;
import java.util.*;

public class RMazeGen {
    public static Random rand = new Random();

    public static String[][] randomMazeGen (int verSize, int horSize) {
        String[][] maze = new String[verSize][horSize];
        for (int i = 0; i < verSize; i++) {
            for (int j = 0; j < horSize; j++) {
                int temp = rand.nextInt(2) + 1;
                if (temp == 1) {
                    maze[i][j] = "B";
                } if (temp == 2) {
                    maze[i][j] = "O";
                }
            }
        }
        int verLocalOfS, horLocalOfS, verLocalOfX, horLocalOfX;
        verLocalOfS = rand.nextInt(verSize - 1);
        verLocalOfX = rand.nextInt(verSize - 1);
        horLocalOfS = rand.nextInt(horSize - 1);
        horLocalOfX = rand.nextInt(horSize - 1);

        maze[verLocalOfS][horLocalOfS] = "S";
        maze[verLocalOfX][horLocalOfX] = "X";

        if (true) for (int i = 0; i < verSize; i++) {
            System.out.println(Arrays.toString(maze[i]));
        }
        return maze;
    }
}
