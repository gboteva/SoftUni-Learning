package exams.august2021;

import java.util.Scanner;

public class FormulaOne {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int matrixSize = Integer.parseInt(scanner.nextLine());
        int countCommands = Integer.parseInt(scanner.nextLine());
        String[][] matrix = new String[matrixSize][matrixSize];
        fillMatrix(matrix, scanner);

        int[] playerCoordinates = getPlayerPlace(matrix);
        int playerRow = playerCoordinates[0];
        int playerCol = playerCoordinates[1];

        boolean isReachedFinish = false;

        while (countCommands-- > 0) {
            matrix[playerRow][playerCol] = ".";
            String direction = scanner.nextLine();

            switch (direction) {
                case "up":
                    playerRow--;
                    break;
                case "down":
                    playerRow++;
                    break;
                case "left":
                    playerCol--;
                    break;
                case "right":
                    playerCol++;
                    break;
            }

            boolean isPlayerOutOfField = checkIsPlayerOut(playerRow, playerCol, matrixSize);

            if (!isPlayerOutOfField) {
                boolean isBonus = matrix[playerRow][playerCol].equals("B");
                boolean isTrap = matrix[playerRow][playerCol].equals("T");
                boolean isFinal = matrix[playerRow][playerCol].equals("F");

                if (isBonus) {
                    playerCoordinates = geForward(direction, playerRow, playerCol);
                    playerRow = playerCoordinates[0];
                    playerCol = playerCoordinates[1];
                    isPlayerOutOfField = checkIsPlayerOut(playerRow, playerCol, matrixSize);

                } else if (isTrap) {
                    playerCoordinates = getBack(direction, playerRow, playerCol);
                    playerRow = playerCoordinates[0];
                    playerCol = playerCoordinates[1];
                    isPlayerOutOfField = checkIsPlayerOut(playerRow, playerCol, matrixSize);

                } else if (isFinal) {
                    isReachedFinish = true;
                    matrix[playerRow][playerCol] = "P";
                    break;

                } else {
                    matrix[playerRow][playerCol] = "P";
                }
            }

            if (isPlayerOutOfField) {
                //he comes in from the other side
                playerCoordinates = getOutsideCoordinates(direction, playerRow, playerCol, matrix);
                playerRow = playerCoordinates[0];
                playerCol = playerCoordinates[1];
                boolean isBonus = matrix[playerRow][playerCol].equals("B");
                boolean isTrap = matrix[playerRow][playerCol].equals("T");
                boolean isFinal = matrix[playerRow][playerCol].equals("F");

                if (isBonus) {
                    playerCoordinates = geForward(direction, playerRow, playerCol);
                    playerRow = playerCoordinates[0];
                    playerCol = playerCoordinates[1];
                    isPlayerOutOfField = checkIsPlayerOut(playerRow, playerCol, matrixSize);

                } else if (isTrap) {
                    playerCoordinates = getBack(direction, playerRow, playerCol);
                    playerRow = playerCoordinates[0];
                    playerCol = playerCoordinates[1];
                    isPlayerOutOfField = checkIsPlayerOut(playerRow, playerCol, matrixSize);
                } else if (isFinal) {
                    isReachedFinish = true;
                    matrix[playerRow][playerCol] = "P";
                    break;
                } else {
                    matrix[playerRow][playerCol] = "P";
                }
            }
        }

        if (isReachedFinish) {
            System.out.println("Well done, the player won first place!");
        } else {
            System.out.println("Oh no, the player got lost on the track!");
        }
        printMatrix(matrix);

    }

    private static int[] geForward(String direction, int playerRow, int playerCol) {
        switch (direction) {
            case "up":
                playerRow--;
                break;
            case "down":
                playerRow++;
                break;
            case "left":
                playerCol--;
                break;
            case "right":
                playerCol++;
                break;
        }
        int[] newCoordinate = new int[2];
        newCoordinate[0] = playerRow;
        newCoordinate[1] = playerCol;
        return newCoordinate;
    }

    private static void printMatrix(String[][] matrix) {
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[r].length; c++) {
                System.out.print(matrix[r][c]);
            }
            System.out.println();
        }
    }

    private static int[] getBack(String direction, int playerRow, int playerCol) {
        switch (direction) {
            case "up":
                playerRow++;
                break;
            case "down":
                playerRow--;
                break;
            case "left":
                playerCol++;
                break;
            case "right":
                playerCol--;
                break;
        }
        int[] newCoordinate = new int[2];
        newCoordinate[0] = playerRow;
        newCoordinate[1] = playerCol;
        return newCoordinate;
    }

    private static int[] getOutsideCoordinates(String direction, int playerRow, int playerCol, String[][] matrix) {
        switch (direction) {
            case "up":
                playerRow = matrix.length - 1;
                break;
            case "down":
                playerRow = 0;
                break;
            case "left":
                playerCol = matrix.length - 1;
                break;
            case "right":
                playerCol = 0;
                break;
        }

        int[] newCoordinate = new int[2];
        newCoordinate[0] = playerRow;
        newCoordinate[1] = playerCol;
        return newCoordinate;
    }

    private static boolean checkIsPlayerOut(int playerRow, int playerCol, int matrixSize) {
        return playerRow < 0 || playerRow >= matrixSize || playerCol < 0 || playerCol >= matrixSize;
    }

    private static int[] getPlayerPlace(String[][] matrix) {
        int[] coordinates = new int[2];
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[r].length; c++) {
                if (matrix[r][c].equals("P")) {
                    coordinates[0] = r;
                    coordinates[1] = c;
                    break;
                }
            }
        }
        return coordinates;
    }

    private static void fillMatrix(String[][] matrix, Scanner scanner) {
        for (int r = 0; r < matrix.length; r++) {
            String[] row = scanner.nextLine().split("");
            matrix[r] = row;
        }

    }
}

