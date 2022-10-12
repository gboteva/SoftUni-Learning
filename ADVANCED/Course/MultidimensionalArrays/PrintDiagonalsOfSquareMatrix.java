package MultidimensionalArrays;

import java.util.Scanner;

public class PrintDiagonalsOfSquareMatrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int dimension = Integer.parseInt(scanner.nextLine());

        String [][] matrix = new String[dimension][];

        for (int i = 0; i < dimension; i++) {
            matrix[i] = scanner.nextLine().split("\\s+");
        }
        int row = 0;
        int col = 0;

        while (row< matrix.length && col<matrix.length){

            System.out.print(matrix[row][col] + " ");

            row++;
            col++;
        }

        System.out.println();

        row = dimension-1;
        col = 0;
        while (row >=0 && col<dimension){

            System.out.print(matrix[row][col] + " ");

            row--;
            col++;
        }
    }
}
