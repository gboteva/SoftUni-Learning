package exams.fourth;

import java.util.Scanner;

public class Bee {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        String[][] matrix = new String[n][n];
        fillTheMatrix(scanner, n, matrix);
        int beeRow = getRow(matrix);
        int beeCol = getCol(matrix);

        String move = scanner.nextLine();
        int countPollinateFlowers = 0;
        boolean isLost = false;

        while (!move.equals("End")) {
            matrix[beeRow][beeCol] = ".";
            switch (move) {
                case "up":
                    beeRow -= 1;
                    break;

                case "down":
                    beeRow += 1;
                    break;

                case "right":
                    beeCol += 1;
                    break;

                case "left":
                    beeCol -= 1;
                    break;
            }

            isLost = checkBoundries(matrix, beeRow, beeCol);
            if (isLost) {
                break;
            }

            if (matrix[beeRow][beeCol].equals("f")) {
                countPollinateFlowers++;
                matrix[beeRow][beeCol] = "B";
                move = scanner.nextLine();

            } else if (matrix[beeRow][beeCol].equals("O")) {
                matrix[beeRow][beeCol] = "B";
            } else if (matrix[beeRow][beeCol].equals(".")) {
                matrix[beeRow][beeCol] = "B";
                move = scanner.nextLine();
            }

        }
        if (isLost) {
            System.out.println("The bee got lost!");
        }
        if (countPollinateFlowers < 5) {
            System.out.println("The bee couldn't pollinate the flowers, she needed " + (5 - countPollinateFlowers) + " flowers more");
        } else {
            System.out.println("Great job, the bee manage to pollinate " + countPollinateFlowers + " flowers!");
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

    private static boolean checkBoundries(String[][] matrix, int beeRow, int beeCol) {
        return beeRow < 0 || beeCol < 0 || beeRow > matrix.length - 1 || beeCol > matrix.length - 1;
    }

    private static int getCol(String[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j].equals("B")) {
                    return j;
                }
            }
        }
        return -1;
    }

    private static int getRow(String[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j].equals("B")) {
                    return i;
                }
            }
        }
        return -1;
    }

    private static void fillTheMatrix(Scanner scanner, int n, String[][] matrix) {
        for (int i = 0; i < n; i++) {
            String[] row = scanner.nextLine().split("");
            matrix[i] = row;
        }
    }
}
