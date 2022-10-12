package exams.secondExam;

import java.util.Scanner;

public class P02_Revolt {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int countOfCommands = Integer.parseInt(scanner.nextLine());

        String[][] matrix = new String[n][n];
        fillMatrix(scanner, matrix);

        boolean isOutside = false;
        boolean isWin = false;

        for (int i = 0; i < countOfCommands; i++) {
            String command = scanner.nextLine();

            int playerRow = getRow(matrix);
            int playerCol = getCol(matrix);
            matrix[playerRow][playerCol] = "-";

            switch (command) {
                case "up":
                    playerRow -= 1;
                    break;
                case "down":
                    playerRow += 1;
                    break;
                case "right":
                    playerCol += 1;
                    break;
                case "left":
                    playerCol -= 1;
                    break;
            }

            isOutside = isOutsideTheMatrix(playerRow, playerCol, matrix);
            if (isOutside) {
                playerRow = getNewRow(matrix, playerRow);
                playerCol = getNewCol(matrix, playerCol);
                isOutside = false;
            }


            if (matrix[playerRow][playerCol].equals("B")) {
                if (command.equals("up")) {
                    playerRow -= 1;
                } else if (command.equals("down")) {
                    playerRow += 1;
                } else if (command.equals("right")) {
                    playerCol += 1;
                } else if (command.equals("left")) {
                    playerCol -= 1;
                }
            }
            isOutside = isOutsideTheMatrix(playerRow, playerCol, matrix);
            if (isOutside) {
                playerRow = getNewRow(matrix, playerRow);
                playerCol = getNewCol(matrix, playerCol);
                isOutside = false;
            }
            if (matrix[playerRow][playerCol].equals("T")) {
                if (command.equals("up")) {
                    playerRow += 1;
                } else if (command.equals("down")) {
                    playerRow -= 1;
                } else if (command.equals("right")) {
                    playerCol -= 1;
                } else if (command.equals("left")) {
                    playerCol += 1;
                }
            }
            isOutside = isOutsideTheMatrix(playerRow, playerCol, matrix);
            if (isOutside) {
                playerRow = getNewRow(matrix, playerRow);
                playerCol = getNewCol(matrix, playerCol);
                isOutside = false;
            }

            if (matrix[playerRow][playerCol].equals("F")) {
                matrix[playerRow][playerCol] = "f";
                isWin = true;
                break;
            }

            isOutside = isOutsideTheMatrix(playerRow, playerCol, matrix);
            if (isOutside) {
                playerRow = getNewRow(matrix, playerRow);
                playerCol = getNewCol(matrix, playerCol);
                isOutside = false;
            }
            matrix[playerRow][playerCol] = "f";
        }


        if (isWin) {
            System.out.println("Player won!");
        } else {
            System.out.println("Player lost!");
        }
        printMatrix(matrix);


    }

    private static void printMatrix(String[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }

    private static int getNewCol(String[][] matrix, int playerCol) {
        if (playerCol < 0) {
            playerCol = matrix.length - 1;
        } else if (playerCol >= matrix.length) {
            playerCol = 0;
        }
        return playerCol;
    }

    private static int getNewRow(String[][] matrix, int playerRow) {
        if (playerRow < 0) {
            playerRow = matrix.length - 1;
        } else if (playerRow >= matrix.length) {
            playerRow = 0;
        }
        return playerRow;
    }


    private static boolean isOutsideTheMatrix(int playerRow, int playerCol, String[][] matrix) {
        if (playerRow < 0 || playerCol < 0 || playerRow >= matrix.length || playerCol >= matrix.length) {
            return true;
        }
        return false;
    }


    private static int getCol(String[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j].equals("f")) {
                    return j;
                }
            }
        }
        return -1;
    }

    private static int getRow(String[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j].equals("f")) {
                    return i;
                }
            }
        }
        return -1;
    }

    private static void fillMatrix(Scanner scanner, String[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            String[] row = scanner.nextLine().split("");
            matrix[i] = row;
        }
    }
}
