package MultidimensionalArrays;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

public class DiagonalDifference {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int[][] matrix = new int[n][n];
        fillMatrix(matrix, scanner);

        int row = 0;
        int col = 0;
        int primaryDiagonalSum = 0;
        while (row < matrix.length && col < matrix.length) {
            primaryDiagonalSum += matrix[row][col];
            row++;
            col++;
        }

        row = matrix.length - 1;
        col = 0;
        int secondaryDiagonal = 0;
        while (row >= 0 && col < matrix.length) {
            secondaryDiagonal+=matrix[row][col];
            row--;
            col++;
        }

        System.out.println(Math.abs(primaryDiagonalSum-secondaryDiagonal));
    }

    private static void fillMatrix(int[][] matrix, Scanner scanner) {

        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt).toArray();
        }
    }


}
