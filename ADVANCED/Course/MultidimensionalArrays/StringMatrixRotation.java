package MultidimensionalArrays;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StringMatrixRotation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] inputCommand = scanner.nextLine().split("[()]");
        int angle = Integer.parseInt(inputCommand[1]) % 360;

        String word = scanner.nextLine();
        List<String> words = new ArrayList<>();

        while (!word.equals("END")) {
            words.add(word);
            word = scanner.nextLine();
        }
        int matrixRow = words.size();
        int matrixCols = getMatrixCols(words);

        char[][] matrix = new char[matrixRow][matrixCols];
        fillMatrix(matrix, words, matrixCols);

        switch (angle) {
            case 0:
                for (int row = 0; row < matrix.length; row++) {
                    for (int col = 0; col < matrix[row].length; col++) {
                        System.out.print(matrix[row][col]);
                    }
                    System.out.println();
                }
                break;
            case 90:
                for (int col = 0; col < matrixCols; col++) {
                    for (int row = matrix.length - 1; row >= 0; row--) {
                        System.out.print(matrix[row][col]);
                    }
                    System.out.println();
                }
                break;
            case 180:
                for (int row = matrix.length - 1; row >= 0; row--) {
                    for (int col = matrixCols - 1; col >= 0; col--) {
                        System.out.print(matrix[row][col]);
                    }
                    System.out.println();
                }
                break;
            case 270:
                for (int col = matrixCols - 1; col >= 0; col--) {
                    for (int row = 0; row < matrixRow; row++) {
                        System.out.print(matrix[row][col]);
                    }
                    System.out.println();
                }
                break;
        }


    }

    private static void fillMatrix(char[][] matrix, List<String> words, int matrixCols) {

        for (int i = 0; i < matrix.length; i++) {
            char[] row = words.get(i).toCharArray();
            if (row.length < matrixCols) {

                for (int k = 0; k < row.length; k++) {
                    matrix[i][k] = row[k];
                }
                for (int k = row.length; k < matrixCols; k++) {
                    matrix[i][k] = ' ';
                }

            } else {
                for (int k = 0; k < row.length; k++) {
                    matrix[i][k] = row[k];
                }

            }
        }
    }

    private static int getMatrixCols(List<String> words) {
        int maxLength = Integer.MIN_VALUE;
        for (String word : words) {
            if (word.length() > maxLength) {
                maxLength = word.length();
            }
        }
        return maxLength;
    }
}
