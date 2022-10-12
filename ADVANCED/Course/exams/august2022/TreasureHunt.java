package exams.august2022;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TreasureHunt {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] dimensions = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        int rows = dimensions[0];
        int cols = dimensions[1];
        String[][] matrix = new String[rows][cols];
        fillMatrix(rows, scanner, matrix);

        int[] myCoordinates = findMe(matrix);
        int myRow = myCoordinates[0];
        int myCol = myCoordinates[1];

        List<String> directions = new ArrayList<>();

        String command = scanner.nextLine();

        while (!command.equals("Finish")) {

            switch (command) {
                case "up":
                    myRow--;
                    break;
                case "down":
                    myRow++;
                    break;
                case "right":
                    myCol++;
                    break;
                case "left":
                    myCol--;
                    break;
            }

            boolean isInField = checkCoordinates(rows, cols, myRow, myCol);

            if (!isInField) {
                myCoordinates = backToTheField(rows, cols, myRow, myCol);
                myRow = myCoordinates[0];
                myCol = myCoordinates[1];

            } else {

                boolean isTree = matrix[myRow][myCol].equals("T");

                if (isTree) {
                    matrix[myRow][myCol] = "-";
                    myCoordinates = goBack(command, myRow, myCol);
                    myRow = myCoordinates[0];
                    myCol = myCoordinates[1];
                } else {
                    directions.add(command);
                    myCoordinates[0] = myRow;
                    myCoordinates[1] = myCol;
                }
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

    private static int[] goBack(String command, int myRow, int myCol) {
        int[] coordinates = new int[2];
        switch (command) {
            case "up":
                myRow++;
                break;
            case "down":
                myRow--;
                break;
            case "right":
                myCol--;
                break;
            case "left":
                myCol++;
                break;
        }
        coordinates[0] = myRow;
        coordinates[1] = myCol;
        return coordinates;
    }

    private static int[] backToTheField(int rows, int cols, int myRow, int myCol) {
        int[] coordinates = new int[2];

        if (myCol < 0) {
            myCol = 0;
        }
        if (myCol == cols) {
            myCol = cols - 1;
        }
        if (myRow < 0) {
            myRow = 0;
        }
        if (myRow == rows) {
            myRow = rows - 1;
        }

        coordinates[0] = myRow;
        coordinates[1] = myCol;
        return coordinates;
    }


    private static boolean checkCoordinates(int rows, int cols, int myRow, int myCol) {
        return myRow >= 0 && myRow < rows && myCol >= 0 && myCol < cols;
    }

    private static int[] findMe(String[][] matrix) {
        int[] myDimensions = new int[2];
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col].equals("Y")) {
                    myDimensions[0] = row;
                    myDimensions[1] = col;
                    break;
                }
            }
        }
        return myDimensions;
    }

    private static void fillMatrix(int rows, Scanner scanner, String[][] matrix) {
        for (int row = 0; row < rows; row++) {
            String[] currentRow = scanner.nextLine().split(" ");
            matrix[row] = currentRow;
        }
    }

}