package Exercises.p05_JediGalaxy;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] dimestions = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int row = dimestions[0];
        int col = dimestions[1];

        int[][] matrix = new int[row][col];

        fillMatrix(row, col, matrix);

        String command = scanner.nextLine();

        long sum = 0;
        while (!command.equals("Let the Force be with you")) {
            int[] peterCoordinates = Arrays.stream(command.split("\\s+")).mapToInt(Integer::parseInt).toArray();
            int[] evilCoordinates = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
            int evilRow = evilCoordinates[0];
            int evilCol = evilCoordinates[1];

            evilDestroy(matrix, evilRow, evilCol);

            int peterRow = peterCoordinates[0];
            int peterCol = peterCoordinates[1];

            while (peterRow >= 0 && peterCol < matrix[1].length) {
                if (isValidCoordinates(peterRow, matrix, peterCol)) {
                    sum += matrix[peterRow][peterCol];
                }

                peterCol++;
                peterRow--;
            }

            command = scanner.nextLine();
        }

        System.out.println(sum);


    }

    private static void evilDestroy(int[][] matrix, int evilRow, int evilCol) {
        while (evilRow >= 0 && evilCol >= 0) {
            if (isValidCoordinates(evilRow, matrix, evilCol)) {
                matrix[evilRow][evilCol] = 0;
            }
            evilRow--;
            evilCol--;
        }
    }

    private static boolean isValidCoordinates(int row, int[][] matrix, int col) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[0].length;
    }

    private static void fillMatrix(int row, int col, int[][] matrix) {
        int value = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = value++;
            }
        }
    }
}
