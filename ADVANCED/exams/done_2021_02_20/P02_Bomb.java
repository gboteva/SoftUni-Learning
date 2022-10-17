package exams.sixth;

import java.util.Scanner;

public class P02_Bomb {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        String[] moves = scanner.nextLine().split(",");

        String[][] matrix = new String[n][];
        fillMatrix(scanner, matrix);
        int row = getRow (matrix);
        int col = getCol (matrix);

        boolean isOut = false;
        boolean isEndOfTheRoad = false;
        int bombCount =0;

        for (int i = 0; i < moves.length; i++) {
            String move = moves[i];
            matrix[row][col] = "+";

            switch (move){
                case "up":
                    row-=1;
                    break;
                case "down":
                    row+=1;
                    break;
                case "left":
                    col-=1;
                    break;
                case "right":
                    col+=1;
                    break;
            }

            isOut = isOutside (matrix, row, col);
            if (isOut){
                if (row>= matrix.length){
                    row = matrix.length-1;
                }
                if (row<0){
                    row = 0;
                }
                if (col>=matrix.length){
                    col = matrix.length - 1;
                }
                if (col<0){
                    col = 0;
                }
            }

            if (matrix[row][col].equals("B")){
                matrix[row][col] = "+";
                bombCount++;
                System.out.println("You found a bomb!");
            }else if (matrix[row][col].equals("e")){
                isEndOfTheRoad = true;
                break;
            }

        }

        boolean isAnotherBomb = checkBombs (matrix);

        if (!isAnotherBomb){
            System.out.println("Congratulations! You found all bombs!");
        }else if (isEndOfTheRoad){
            int countLeftBombs = getLeftBOmbs(matrix);
            System.out.println("END! " + countLeftBombs + " bombs left on the field");
        }else {
            int countLeftBombs = getLeftBOmbs(matrix);
            System.out.printf("%d bombs left on the field. Sapper position: (%d,%d)%n", countLeftBombs, row, col);
        }


    }

    private static int getLeftBOmbs(String[][] matrix) {
        int count = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j].equals("B")){
                    count++;
                }

            }
        }
        return count;
    }

    private static boolean checkBombs(String[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j].equals("B")){
                    return true;
                }

            }
        }
        return false;
    }

    private static boolean isOutside(String[][] matrix, int row, int col) {
        return row<0 || col<0 || row>=matrix.length || col>=matrix.length;
    }

    private static int getCol(String[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j].equals("s")){
                    return j;
                }

            }
        }
        return -1;
    }

    private static int getRow(String[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j].equals("s")){
                    return i;
                }

            }
        }
        return -1;
    }

    private static void fillMatrix(Scanner scanner, String[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            String [] row = scanner.nextLine().split(" ");
            matrix[i] = row;
        }
    }
}
