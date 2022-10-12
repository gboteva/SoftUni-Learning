package multidimensionalArrays_02.ex;

import java.util.Scanner;

public class P03_DiagonalDifference {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int[][] matrix = new int[n][n];
        fillMatrix(matrix, scanner);
        int sumPrimaryDiagonal = getSumOfPrimeryDiagonals(matrix);
        int sumSecondaryDiagonal = getSumOfSecondaryDiagonals(matrix);
        int diff = Math.abs(sumPrimaryDiagonal-sumSecondaryDiagonal);

        System.out.println(diff);
    }

    private static int getSumOfSecondaryDiagonals(int[][] matrix) {
        int sum = 0;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (col == matrix.length - row - 1) {
                    sum += matrix[row][col];
                }
            }
        }
        return sum;
    }

    private static int getSumOfPrimeryDiagonals(int[][] matrix) {
        int sum = 0;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (row == col) {
                    sum += matrix[row][col];
                }
            }
        }
        return sum;
    }

    private static void fillMatrix(int[][] matrix, Scanner scanner) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                matrix[row][col] = scanner.nextInt();
            }
        }

    }
}
