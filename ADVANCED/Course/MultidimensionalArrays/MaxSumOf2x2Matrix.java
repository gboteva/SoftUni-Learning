package MultidimensionalArrays;

import java.util.Arrays;
import java.util.Scanner;

public class MaxSumOf2x2Matrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] dimensions = Arrays.stream(scanner.nextLine().split(", "))
                .mapToInt(Integer::parseInt).toArray();
        int[][] matrix = fillMatrix(scanner, dimensions);

        int maxSum = Integer.MIN_VALUE;
        int [][] maxMatrix = new int[2][2];

        for (int r = 0; r < matrix.length-1; r++) {
            for (int c = 0; c < matrix[r].length -1; c++) {
               int sum = matrix[r][c] + matrix[r][c+1] + matrix[r+1][c] + matrix[r+1][c+1];
               if (sum>maxSum){
                   maxSum = sum;
                   maxMatrix[0][0] = matrix[r][c];
                   maxMatrix[0][1] = matrix[r][c+1];
                   maxMatrix[1][0] = matrix[r+1][c];
                   maxMatrix[1][1] = matrix[r+1][c+1];
               }
            }
        }

        printMatrix(maxMatrix);
        System.out.println(maxSum);

    }

    private static void printMatrix(int[][] matrix) {
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[r].length ; c++) {
                System.out.print(matrix[r][c] + " ");
            }
            System.out.println();
        }
    }

    private static int[][] fillMatrix(Scanner scanner, int[] dimensions) {
        int rows = dimensions[0];
        int cols = dimensions[1];

        int[][] matrix = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            matrix[i] = Arrays.stream(scanner.nextLine().split(", "))
                    .mapToInt(Integer::parseInt).toArray();
        }
        return matrix;
    }
}
