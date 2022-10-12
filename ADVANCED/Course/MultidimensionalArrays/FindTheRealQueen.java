package MultidimensionalArrays;

import java.util.Scanner;

public class FindTheRealQueen {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        char[][] chessBoard = fillMatrix(scanner);
        boolean isRealQueen = false;
        for (int r = 0; r < chessBoard.length; r++) {
            for (int c = 0; c < chessBoard[r].length; c++) {
                if (chessBoard[r][c] == 'q') {
                    isRealQueen = checkRow(chessBoard, r, c)
                            && checkCol(chessBoard, r, c)
                            && checkBasicDiagonal(chessBoard, r, c)
                            && checkSecondaryDiagonal(chessBoard, r, c);

                    if (isRealQueen){
                        System.out.println(r + " " + c);
                        break;
                    }

                }
            }
        }

    }

    private static boolean checkSecondaryDiagonal(char[][] chessBoard, int row, int col) {
        int sum = row + col;
        int startRow = -1;
        int startCol = -1;
        if (sum >= 8) {
            startRow = 7;
            startCol = sum - startRow;
        } else {
            startRow = sum;
            startCol = 0;
        }
        int stopRow = startCol;
        int stopCol = startRow;

        int countQueens = 0;
        int c = startCol;
        for (int r = startRow; r >= stopRow; r--) {
            if (chessBoard[r][c] == 'q') {
                countQueens++;
                if (countQueens > 1) {
                    return false;
                }
            }
            c++;
            if (c > stopCol) {
                break;
            }
        }

        return true;
    }

    private static boolean checkBasicDiagonal(char[][] chessBoard, int queenRow, int queenCol) {
        int diff = queenRow - queenCol;
        int startRow = -1;
        int startCol = -1;
        if (diff >= 0) {
            startRow = queenRow - queenCol;
            startCol = 0;
        } else {
            startRow = 0;
            startCol = Math.abs(queenRow - queenCol);
        }

        int stopRow = 7 - startCol;
        int stopCol = 7 - startRow;


        int countQueens = 0;
        int c = startCol;
        for (int r = startRow; r <= stopRow; r++) {
            if (chessBoard[r][c] == 'q') {
                countQueens++;
                if (countQueens > 1) {
                    return false;
                }
            }
            c++;
            if (c > stopCol) {
                break;
            }
        }

        return true;
    }

    private static boolean checkCol(char[][] chessBoard, int queenRow, int queenCol) {
        int row = 0;

        while (row < chessBoard.length) {

            if (chessBoard[row][queenCol] == 'q' && row != queenRow) {
                return false;
            }

            row++;
        }
        return true;
    }

    private static boolean checkRow(char[][] chessBoard, int queenRow, int queenCol) {

        int col = 0;

        while (col < chessBoard.length) {
            if (chessBoard[queenRow][col] == 'q' && (col != queenCol)) {
                return false;
            }
            col++;
        }
        return true;

    }

    private static int[] getCoordinates(char[][] chessBoard) {
        int[] coordinates = new int[2];
        for (int r = 0; r < chessBoard.length; r++) {
            for (int c = 0; c < chessBoard[r].length; c++) {
                if (chessBoard[r][c] == 'q') {
                    coordinates[0] = r;
                    coordinates[1] = c;
                    break;
                }
            }
        }
        return coordinates;
    }

    private static char[][] fillMatrix(Scanner scanner) {
        char[][] matrix = new char[8][8];

        for (int i = 0; i < matrix.length; i++) {
            String[] rowOfMatrix = scanner.nextLine().split("\\s");
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = rowOfMatrix[j].charAt(0);
            }
        }
        return matrix;
    }
}
