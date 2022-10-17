package exams.august2022;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TreasureHunt {

    private static int myRow;
    private static int myCol;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] dimensions = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        int rows = dimensions[0];
        int cols = dimensions[1];

        String[][] matrix = new String[rows][cols];

        for (int r = 0; r < rows; r++) {
            String[] row = scanner.nextLine().split("\\s+");
            matrix[r] = row;
            for (int i = 0; i < row.length; i++) {
                if (row[i].equals("Y")) {
                    myRow = r;
                    myCol = i;
                }
            }
        }

        List<String> directions = new ArrayList<>();

        String command = scanner.nextLine();

        while (!command.equals("Finish")) {

            switch (command) {
                case "up":
                    move(matrix, -1, 0, directions, command);
                    break;
                case "down":
                    move(matrix, 1, 0, directions, command);
                    break;
                case "right":
                    move(matrix, 0, 1, directions, command);
                    break;
                case "left":
                    move(matrix, 0, -1, directions, command);
                    break;
            }


            command = scanner.nextLine();
        }

        if (matrix[myRow][myCol].equals("X")) {
            System.out.println("I've found the treasure!");
            System.out.println("The right path is " + String.join(", ", directions));
        } else {
            System.out.println("The map is fake!");
        }

    }

    private static void move(String[][] matrix, int rowMutator, int colMutator, List<String> directions, String command) {
        int nextRow = myRow + rowMutator;
        int nextCol = myCol + colMutator;

        if (isOutOfField(matrix, nextRow, nextCol)) {
            if (nextRow < 0 || nextRow >= matrix.length) {
                nextRow = nextRow < 0 ? 0 : matrix.length - 1;
            } else {
                nextCol = nextCol < 0 ? 0 : matrix[nextRow].length - 1;
            }
            myRow = nextRow;
            myCol = nextCol;
            return;
        }

        if (matrix[nextRow][nextCol].equals("T")) {
            return;
        }

        myRow = nextRow;
        myCol = nextCol;
        directions.add(command);

    }

    private static boolean isOutOfField(String[][] matrix, int nextRow, int nextCol) {
        return nextRow < 0 || nextRow >= matrix.length || nextCol < 0 || nextCol >= matrix[nextRow].length;
    }


}