package MultidimensionalArrays;

import java.util.Arrays;
import java.util.Scanner;

public class SumMatrixElements {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] dimensions = Arrays.stream(scanner.nextLine().split(", "))
                .mapToInt(Integer::parseInt).toArray();

        int[][] matrix = fillMatrix(scanner, dimensions);
        int sum = getSumOfMatrixElement(matrix);
        System.out.println(dimensions[0]);
        System.out.println(dimensions[1]);
        System.out.println(sum);
    }

    private static int getSumOfMatrixElement(int[][] matrix) {
        int sum = 0;
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[r].length ; c++) {
                sum+=matrix[r][c];
            }
        }
        return sum;
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
