package MultidimensionalArrays;

import java.util.Scanner;

public class FillTheMatrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String [] input = scanner.nextLine().split(", ");
        int n = Integer.parseInt(input[0]);
        String pattern = input[1];

        int [][] matrix = new int[n][n];

        if (pattern.equals("A")){
            matrix = fillMatrixA(matrix);
        }else {
            matrix = fillMatrixB(matrix);
        }

        printMatrix(matrix);

    }

    private static void printMatrix(int[][] matrix) {
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[r].length ; c++) {
                System.out.print(matrix[r][c] + " ");
            }
            System.out.println();
        }
    }

    private static int[][] fillMatrixB(int [][] matrix) {
        int value = 1;
        for (int col = 0; col < matrix.length; col++) {

            if (col % 2 == 0) {
                for (int row = 0; row < matrix.length; row++) {
                    matrix[row][col] = value;
                    value++;
                }
            }else {
                for (int row = matrix.length-1; row >=0; row--) {
                    matrix[row][col] = value;
                    value++;
                }
            }
        }
        return matrix;
    }

    private static int[][] fillMatrixA(int [][] matrix) {
        int value = 1;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length ; j++) {
                 matrix[j][i] = value++;
            }
        }
        return matrix;
    }


}
