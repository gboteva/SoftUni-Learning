package MultidimensionalArrays;

import java.util.Arrays;
import java.util.Scanner;

public class WrongMeasurements {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        int[][] matrix = new int[n][];

        for (int i = 0; i < n; i++) {
            matrix[i] = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt).toArray();
        }

        String[] coordinates = scanner.nextLine().split("\\s+");
        int row = Integer.parseInt(coordinates[0]);
        int col = Integer.parseInt(coordinates[1]);
        int wrongElement = matrix[row][col];

        int[][] fixedMatrix = copyMatrix(matrix);



        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {

                if (matrix[i][j] == wrongElement) {
                    int aroundSum = getSum(matrix, i, j, wrongElement);
                    fixedMatrix[i][j] = aroundSum;
                } else {
                    fixedMatrix[i][j] = matrix[i][j];
                }

            }
        }

        printMatrix(fixedMatrix);

    }

    private static int[][] copyMatrix(int[][] matrix) {
        int [][] fixed = new int[matrix.length][];
        for (int i = 0; i < matrix.length; i++) {
            fixed[i] = matrix[i].clone();
        }
        return fixed;
    }


    private static void zeroMatrix(int[][] fixedMatrix) {
        for (int i = 0; i < fixedMatrix.length; i++) {
            for (int j = 0; j < fixedMatrix[i].length; j++) {
                fixedMatrix[i][j] = 0;
            }
        }
    }

    private static void printMatrix(int[][] fixedMatrix) {
        for (int r = 0; r < fixedMatrix.length; r++) {
            for (int c = 0; c < fixedMatrix[r].length; c++) {
                System.out.print(fixedMatrix[r][c] + " ");
            }
            System.out.println();
        }
    }

    private static int getSum(int[][] matrix, int row, int col, int wrong) {

        int upElement = row == 0 || matrix[row - 1][col] == wrong ? 0 : matrix[row - 1][col];
        int downElement = row == matrix.length - 1 ||matrix[row + 1][col] == wrong ? 0 : matrix[row + 1][col];
        int leftElement = col == 0 ||matrix[row][col - 1] == wrong ? 0 : matrix[row][col - 1];
        int rightElement = col == matrix[row].length - 1 || matrix[row][col+1] ==wrong ? 0 : matrix[row][col + 1];


        return upElement + downElement + leftElement + rightElement;
    }
}
