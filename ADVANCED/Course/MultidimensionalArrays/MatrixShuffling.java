package MultidimensionalArrays;

import java.util.Arrays;
import java.util.Scanner;

public class MatrixShuffling {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] dimensions = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();
        String[][] matrix = fillMatrix(scanner, dimensions);

        String input = scanner.nextLine();

        while (!input.equals("END")) {
            if (isValidInput(input, matrix, dimensions)) {
                int row1 = Integer.parseInt(input.split("\\s+")[1]);
                int col1 = Integer.parseInt(input.split("\\s+")[2]);
                int row2 = Integer.parseInt(input.split("\\s+")[3]);
                int col2 = Integer.parseInt(input.split("\\s+")[4]);
                String firstElement = matrix[row1][col1];
                String secondElement = matrix[row2][col2];
                matrix[row1][col1] = secondElement;
                matrix[row2][col2] = firstElement;
                print(matrix);
            } else {
                System.out.println("Invalid input!");
            }

            input = scanner.nextLine();
        }

    }

    private static void print(String[][] matrix) {
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[r].length; c++) {
                System.out.print(matrix[r][c] + " ");
            }
            System.out.println();
        }
    }

    private static boolean isValidInput(String input, String[][] matrix, int[] dimensions) {
        String command = input.split("\\s+")[0];
        if (!command.equals("swap")) {
            return false;
        }

        if (input.split("\\s+").length != 5) {
            return false;
        }

        int row1 = Integer.parseInt(input.split("\\s+")[1]);
        int col1 = Integer.parseInt(input.split("\\s+")[2]);
        int row2 = Integer.parseInt(input.split("\\s+")[3]);
        int col2 = Integer.parseInt(input.split("\\s+")[4]);

        int rows = dimensions[0];
        int cols = dimensions[1];

        if (row1 < 0 || row1 >= rows) {
            return false;
        }

        if (row2 < 0 || row2 >= rows) {
            return false;
        }

        if (col1 < 0 || col1 >= cols) {
            return false;
        }


        if (col2 < 0 || col2 >= cols) {
            return false;
        }
        return true;
    }

    private static String[][] fillMatrix(Scanner scanner, int[] dimensions) {
        int rows = dimensions[0];
        int cols = dimensions[1];

        String[][] matrix = new String[rows][cols];

        for (int i = 0; i < rows; i++) {
            matrix[i] = scanner.nextLine().split("\\s+");

        }
        return matrix;

    }
}
