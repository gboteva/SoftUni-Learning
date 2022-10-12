package MultidimensionalArrays;

import java.util.Scanner;

public class IntersectionOfTwoMatrices {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int row = Integer.parseInt(scanner.nextLine());
        int col = Integer.parseInt(scanner.nextLine());

        char[][] matrixA = fillMatrix(row, col, scanner);
        char[][] matrixB = fillMatrix(row, col, scanner);

        char[][] matrixC = new char[row][col];

        for (int r = 0; r < matrixA.length; r++) {
            for (int c = 0; c < matrixA[r].length ; c++) {
                if (matrixA[r][c] == matrixB[r][c]){
                    matrixC[r][c] = matrixA[r][c];
                }else {
                    matrixC[r][c] = '*';
                }
            }
        }

        printMatrix(matrixC);

    }

    private static void printMatrix(char[][] matrixC) {
        for (int r = 0; r < matrixC.length; r++) {
            for (int c = 0; c < matrixC[r].length ; c++) {
                System.out.print(matrixC[r][c] + " ");
            }
            System.out.println();
        }
    }

    private static char[][] fillMatrix(int row, int col, Scanner scanner) {
        char[][] matrix = new char[row][col];

        for (int i = 0; i < matrix.length; i++) {
            String [] rowOfMatrix = scanner.nextLine().split("\\s");
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = rowOfMatrix[j].charAt(0);
            }
        }
        return matrix;
    }
}
