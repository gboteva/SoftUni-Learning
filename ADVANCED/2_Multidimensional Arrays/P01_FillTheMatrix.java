package multidimensionalArrays_02.ex;

import java.util.Scanner;

public class P01_FillTheMatrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(", ");
        int count = Integer.parseInt(input[0]);
        String pattern = input[1];
        int[][] matrix = new int[count][count];

        switch (pattern){
            case "A":

                matrix=fillMatrixPatternA(matrix);
                printMatrix(matrix);

                break;
            case "B":

                matrix=fillMatrixPatternB(matrix);
                printMatrix(matrix);
                break;
        }




    }

    private static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static int[][] fillMatrixPatternB(int[][] matrixB) {
        int num = 1;
        for (int col = 0; col < matrixB.length; col++) {

            if (col % 2 == 0) {
                for (int row = 0; row < matrixB.length; row++) {
                    matrixB[row][col] = num;
                    num++;
                }
            }else {
                for (int row = matrixB.length-1; row >=0; row--) {
                    matrixB[row][col] = num;
                    num++;
                }
            }
        }
        return matrixB;
    }

    private static int[][] fillMatrixPatternA(int[][] matrixA) {
        int number = 1;

        for (int col = 0; col < matrixA.length; col++) {
            for (int row = 0; row < matrixA[col].length; row++) {
                matrixA[row][col] = number;
                number++;
            }
        }
        return matrixA;
    }


}
