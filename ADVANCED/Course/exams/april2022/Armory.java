package exams.april2022;

import java.util.Scanner;

public class Armory {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        char[][] matrix = new char[n][n];
        fillMatrix(matrix, scanner);

        int[] officerPosition = getPosition(matrix);
        int row = officerPosition[0];
        int col = officerPosition[1];

        int totalPrice = 0;
        boolean isInField = true;

        while (totalPrice < 65) {
            String move = scanner.nextLine();
            matrix[row][col] = '-';

            switch (move) {
                case "up":
                    row--;
                    break;
                case "down":
                    row++;
                    break;
                case "left":
                    col--;
                    break;
                case "right":
                    col++;
                    break;
            }
            isInField = isInBoundaries(n, row, col);

            if (!isInField) {
                break;
            } else {
                char place = matrix[row][col];
                if (Character.isDigit(place)){
                    int price = Integer.parseInt(String.valueOf(place));
                    totalPrice+=price;
                    matrix[row][col] = 'A';
                } else if (place == 'M'){
                    matrix[row][col] = '-';
                    officerPosition = getMirorPosition(matrix, row, col);
                    row = officerPosition[0];
                    col = officerPosition[1];
                    matrix[row][col] = 'A';
                }
            }
        }

        if (!isInField) {
            System.out.println("I do not need more swords!");
        }else {
            System.out.println("Very nice swords, I will come back for more!");
        }

        System.out.printf("The king paid %d gold coins.%n", totalPrice);

        printMatrix(matrix);
    }

    private static void printMatrix(char[][] matrix) {
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[r].length ; c++) {
                System.out.print(matrix[r][c]);
            }
            System.out.println();
        }

    }

    private static int[] getMirorPosition(char[][] matrix, int row, int col) {
        int[] mirrorPosition = new int[2];
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[r].length ; c++) {
                if (matrix[r][c] == 'M' && row!=r && col!=c){
                    mirrorPosition[0] = r;
                    mirrorPosition[1] = c;
                }
            }
        }
        return mirrorPosition;
    }

    private static boolean isInBoundaries(int n, int row, int col) {
        return row >= 0 && col >= 0 && row < n && col < n;
    }

    private static int[] getPosition(char[][] matrix) {
        int[] coordinates = new int[2];
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] == 'A') {
                    coordinates[0] = row;
                    coordinates[1] = col;
                    break;
                }
            }
        }
        return coordinates;
    }

    private static void fillMatrix(char[][] matrix, Scanner scanner) {

        for (int row = 0; row < matrix.length; row++) {
            char[] currentRow = scanner.nextLine().toCharArray();
            matrix[row] = currentRow;
        }
    }
}
