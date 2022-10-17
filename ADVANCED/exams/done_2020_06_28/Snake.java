package exams.third;

import java.util.Map;
import java.util.Scanner;

public class Snake {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        int n = Integer.parseInt(scanner.nextLine());

        String[][] matrix = new String[n][n];

        fillMatrix(scanner, matrix, n);

        int foodQuantity = 0;
        boolean isOutside = false;

        int snakeRow = getSnakeRow(matrix);
        int snakeCol = getSnakeCow(matrix);
        matrix[snakeRow][snakeCol] = ".";

        String move = scanner.nextLine();

        while (foodQuantity<10 || !isOutside){

            switch (move){
                case "up":
                    snakeRow-=1;
                    break;

                case "down":
                   //snakeRow = snakeRow + 1;
                   snakeRow+=1;
                    break;

                case "right":
                    snakeCol+=1;
                    break;

                case "left":
                    snakeCol-=1;
                    break;
            }

            isOutside = checkIsItOutside(matrix, snakeRow, snakeCol);
            if (isOutside){
                break;
            }

            if (matrix[snakeRow][snakeCol].equals("*")){
                foodQuantity+=1;

            }else if (matrix[snakeRow][snakeCol].equals("B")){
                matrix[snakeRow][snakeCol] = ".";
                snakeRow = getBRow(matrix);
                snakeCol = getBCol(matrix);
            }

            matrix[snakeRow][snakeCol] = ".";

            if (foodQuantity==10){
                matrix[snakeRow][snakeCol] ="S";
                break;
            }



            move = scanner.nextLine();
        }

        if (isOutside){
            System.out.println("Game over!");
        }else if (foodQuantity==10){
            System.out.println("You won! You fed the snake.");
        }

        System.out.println("Food eaten: " + foodQuantity);
        printMatrix(matrix);


    }

    private static void printMatrix(String[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }

    private static int getBCol(String[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j].equals("B")){
                    return j;
                }

            }
        }
        return -1;
    }

    private static int getBRow(String[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j].equals("B")){
                    return i;
                }
            }
        }
        return -1;
    }

    private static boolean checkIsItOutside(String[][] matrix, int snakeRow, int snakeCol) {
        return snakeRow<0 || snakeRow>matrix.length-1 || snakeCol<0 || snakeCol> matrix.length-1;
    }

    private static int getSnakeCow(String[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j].equals("S")){
                    return j;
                }
            }
        }
        return -1;
    }

    private static int getSnakeRow(String[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j].equals("S")){
                    return i;
                }
            }
        }
        return -1;
    }

    private static void fillMatrix(Scanner scanner, String[][] matrix, int n) {
        for (int i = 0; i < matrix.length; i++) {
            String[] row = scanner.nextLine().split("");
            matrix[i] = row;
        }
    }
}
