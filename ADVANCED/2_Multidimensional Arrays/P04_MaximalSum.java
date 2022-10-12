package multidimensionalArrays_02.ex;

import java.util.Scanner;

public class P04_MaximalSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int [][] matrix = new int[n][m];

        fillMatrix(matrix, scanner);

        int maxSum = Integer.MIN_VALUE;
        int [][] maxMatrix = new int[3][3];

        for (int r = 0; r < n-2; r++) {
            for (int c = 0; c < m-2; c++) {
                int[][] subMatrix = new int[3][3];
                int currentSum = 0;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        subMatrix[i][j] = matrix[r+i][c+j];
                        currentSum += subMatrix[i][j];
                    }
                }
                if (currentSum > maxSum) {
                    maxSum = currentSum;
                    maxMatrix = subMatrix;
                }
            }
        }
        System.out.println("Sum = " + maxSum);
        printMatrix(maxMatrix);

    }

    private static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void fillMatrix(int[][] matrix, Scanner scanner) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                matrix[row][col] = scanner.nextInt();
            }
        }
    }
}
