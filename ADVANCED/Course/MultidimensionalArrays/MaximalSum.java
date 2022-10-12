package MultidimensionalArrays;

import java.util.Arrays;
import java.util.Scanner;

public class MaximalSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] dimensions = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();
        int[][] matrix = fillMatrix(scanner, dimensions);

        int maxSum = Integer.MIN_VALUE;
        int[][] maxMatrix = new int[3][3];

        for (int r = 0; r < matrix.length - 2; r++) {
            for (int c = 0; c < matrix[r].length - 2; c++) {
                int sum = matrix[r][c] + matrix[r][c + 1] + matrix[r][c + 2]
                        + matrix[r + 1][c] + matrix[r + 1][c + 1] + matrix[r + 1][c + 2]
                        + matrix[r + 2][c] + matrix[r + 2][c + 1] + matrix[r + 2][c + 2];

                if (sum > maxSum) {
                    maxSum = sum;
                    maxMatrix[0][0] = matrix[r][c];
                    maxMatrix[0][1] = matrix[r][c + 1];
                    maxMatrix[0][2] = matrix[r][c + 2];

                    maxMatrix[1][0] = matrix[r + 1][c];
                    maxMatrix[1][1] = matrix[r + 1][c + 1];
                    maxMatrix[1][2] = matrix[r + 1][c + 2];

                    maxMatrix[2][0] = matrix[r + 2][c];
                    maxMatrix[2][1] = matrix[r + 2][c + 1];
                    maxMatrix[2][2] = matrix[r + 2][c + 2];
                }
            }
        }
        System.out.println("Sum = " + maxSum);
        print(maxMatrix);

    }

    private static void print(int[][] maxMatrix) {
        for (int r = 0; r < maxMatrix.length; r++) {
            for (int c = 0; c < maxMatrix[r].length; c++) {
                System.out.print(maxMatrix[r][c] + " ");
            }
            System.out.println();
        }

    }

    private static int[][] fillMatrix(Scanner scanner, int[] dimensions) {
        int rows = dimensions[0];
        int cols = dimensions[1];

        int[][] matrix = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            matrix[i] = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt).toArray();

        }
        return matrix;

    }
}
