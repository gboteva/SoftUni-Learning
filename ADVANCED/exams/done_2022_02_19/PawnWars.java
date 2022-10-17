package exams.february2022;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PawnWars {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[][] matrix = new String[8][8];
        fillMatrix(scanner, matrix);
        int[] playersCoordinates = getPlayerCoordinates(matrix);

        boolean isWhiteReachedFinal = false;
        boolean isBlackReachedFinal = false;
        boolean isWhiteOnTurn = true;

        while (!isWhiteReachedFinal && !isBlackReachedFinal) {

            if (isWhiteOnTurn) {
                int whiteRow = playersCoordinates[0];
                int whiteCol = playersCoordinates[1];
                matrix[whiteRow][whiteCol] = "-";

                boolean isAnotherPawnUp = checkDiagonalsUp(matrix, playersCoordinates);
                if (isAnotherPawnUp) {
                    String positionOfWinner = getWinnerPosition(matrix, playersCoordinates[2], playersCoordinates[3]);
                    System.out.printf("Game over! White capture on %s%n" , positionOfWinner);
                    break;
                }

                playersCoordinates = moveUp(playersCoordinates);

                isWhiteReachedFinal = isWhiteWin(playersCoordinates);

                matrix[playersCoordinates[0]][playersCoordinates[1]] = "w";

                isWhiteOnTurn = false;

            } else {
                int blackRow = playersCoordinates[2];
                int blackCol = playersCoordinates[3];
                matrix[blackRow][blackCol] = "-";

                boolean isAnotherPawnDown = checkDiagonalsDown(matrix, playersCoordinates);
                if (isAnotherPawnDown) {
                    String positionOfWinner = getWinnerPosition(matrix, playersCoordinates[0], playersCoordinates[1]);
                    System.out.printf("Game over! Black capture on %s%n" , positionOfWinner);
                    break;
                }

                playersCoordinates = moveDown(playersCoordinates);

                isBlackReachedFinal = isBlackWin(playersCoordinates);

                matrix[playersCoordinates[2]][playersCoordinates[3]] = "b";
                isWhiteOnTurn = true;
            }

        }
        if (isWhiteReachedFinal){
            System.out.println("Game over! White pawn is promoted to a queen at " + getWinnerPosition(matrix, playersCoordinates[0], playersCoordinates[1]));
        }else if (isBlackReachedFinal) {
            System.out.println("Game over! Black pawn is promoted to a queen at " + getWinnerPosition(matrix,playersCoordinates[2], playersCoordinates[3]));
        }

    }

    private static String getWinnerPosition(String[][] matrix, int row, int col) {
        Map<Integer, String> cols = new HashMap<>();
        cols.put(0, "a");
        cols.put(1, "b");
        cols.put(2, "c");
        cols.put(3, "d");
        cols.put(4, "e");
        cols.put(5, "f");
        cols.put(6, "g");
        cols.put(7, "h");

        Map<Integer, String> rows = new HashMap<>();
        rows.put(0, "8");
        rows.put(1, "7");
        rows.put(2, "6");
        rows.put(3, "5");
        rows.put(4, "4");
        rows.put(5, "3");
        rows.put(6, "2");
        rows.put(7, "1");

        StringBuilder builder = new StringBuilder();
        builder.append(cols.get(col)).append(rows.get(row));
        builder.append(".");

        return builder.toString().trim();

    }


    private static boolean checkDiagonalsDown(String[][] matrix, int[] blackCoordinates) {
        int row = blackCoordinates[2];
        int col = blackCoordinates[3];

        if (col-1 >= 0 && matrix[row + 1][col - 1].equals("w")){
            return true;
        }else if (col+1 < 8 && matrix[row + 1][col + 1].equals("w")){
            return true;
        }
        return false;
    }

    private static boolean checkDiagonalsUp(String[][] matrix, int[] whiteCoordinates) {
        int row = whiteCoordinates[0];
        int col = whiteCoordinates[1];

        if (col-1 >= 0 && matrix[row - 1][col - 1].equals("b")){
            return true;
        }else if (col+1 < 8 && matrix[row - 1][col + 1].equals("b")){
            return true;
        }
        return false;
    }

    private static int[] moveDown(int[] blackCoordinates) {
        blackCoordinates[2] = blackCoordinates[2] + 1;
        return blackCoordinates;
    }

    private static int[] moveUp(int[] whiteCoordinates) {
        whiteCoordinates[0] = whiteCoordinates[0] - 1;
        return whiteCoordinates;
    }

    private static boolean isBlackWin(int[] blackCoordinates) {
        return blackCoordinates[2] == 7;
    }

    private static boolean isWhiteWin(int[] whiteCoordinates) {
        return whiteCoordinates[0] == 0;
    }


    private static int[] getPlayerCoordinates(String[][] matrix) {
        int[] coordinates = new int[4];
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col].equals("w")) {
                    coordinates[0] = row;
                    coordinates[1] = col;
                } else if (matrix[row][col].equals("b")) {
                    coordinates[2] = row;
                    coordinates[3] = col;
                }
            }
        }
        return coordinates;
    }

    private static void fillMatrix(Scanner scanner, String[][] matrix) {
        for (int r = 0; r < matrix.length; r++) {
            String[] row = scanner.nextLine().split("");
            matrix[r] = row;
        }
    }
}
