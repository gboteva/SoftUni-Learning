package exams.fifth;

import java.util.Scanner;

public class P02_Selling {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        String [][] matrix = new String[n][];
        fillTheMatrix(scanner, matrix);
        int myRow = getRow(matrix);
        int myCol = getCol(matrix);


        boolean isOutside = false;
        int money = 0;

        while (money<50){
            String move = scanner.nextLine();
            matrix[myRow][myCol] = "-";

            switch (move){
                case "up":
                    myRow-=1;
                    break;
                case "down":
                    myRow+=1;
                    break;
                case "left":
                    myCol-=1;
                    break;
                case "right":
                    myCol+=1;
                    break;
            }

            isOutside = isOutOfBoundaries(matrix, myRow, myCol);
            if (isOutside){
                break;
            }

            if (!matrix[myRow][myCol].equals("-") && !matrix[myRow][myCol].equals("O")){
                int digit = Integer.parseInt(matrix[myRow][myCol]);
                money+=digit;
                matrix[myRow][myCol] = "S";
            }else if (matrix[myRow][myCol].equals("O")){
                matrix[myRow][myCol] = "-";
                int pilarRow = getPilarRow (matrix);
                int pilarCol = getPilarCol (matrix);
                myRow = pilarRow;
                myCol = pilarCol;
                matrix[myRow][myCol] = "S";
            }

        }

        if (isOutside){
            System.out.println("Bad news, you are out of the bakery.");
        }else {
            System.out.println("Good news! You succeeded in collecting enough money!");
        }

        System.out.println("Money: " + money);
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

    private static int getPilarCol(String[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j].equals("O")) {
                    return j;
                }
            }
        }
        return -1;
    }

    private static int getPilarRow(String[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j].equals("O")) {
                    return i;
                }
            }
        }
        return -1;
    }


    private static boolean isOutOfBoundaries(String[][] matrix, int myRow, int myCol) {
        return myRow<0 || myCol<0 || myRow>=matrix.length || myCol>=matrix.length;
    }

    private static int getCol(String[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j].equals("S")) {
                    return j;
                }
            }
        }
        return -1;
    }

    private static int getRow(String[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j].equals("S")) {
                    return i;
                }
            }
        }
        return -1;
    }

    private static void fillTheMatrix(Scanner scanner, String[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = scanner.nextLine().split("");
        }
    }
}
