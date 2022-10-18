package exams.firstExam;

import java.util.Scanner;

public class P02_PresentDelivery {
    private static int santaRow;
    private static int santaCol;
    private static int countOfPresents;
    private static int goodKidsWithPresents;
    private static boolean isOut = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        countOfPresents = Integer.parseInt(scanner.nextLine());

        int size = Integer.parseInt(scanner.nextLine());
        String[][] matrix = new String[size][size];

        for (int i = 0; i < size; i++) {
            String[] row = scanner.nextLine().split("\\s+");
            matrix[i] = row;
            for (int j = 0; j < row.length; j++) {
                if (row[j].equals("S")) {
                    santaRow = i;
                    santaCol = j;
                }
            }
        }

        String command = scanner.nextLine();

        goodKidsWithPresents = 0;
        while (!command.equals("Christmas morning")) {

            switch (command) {
                case "up":
                    move(matrix, -1, 0);
                    break;
                case "down":
                    move(matrix, 1, 0);
                    break;
                case "left":
                    move(matrix, 0, -1);
                    break;
                case "right":
                    move(matrix, 0, 1);
                    break;
            }

            if (isOut) {
                break;
            }

            if (countOfPresents <= 0) {
                break;
            }


            command = scanner.nextLine();
        }
        if (isOut || countOfPresents <= 0) {
            System.out.println("Santa ran out of presents!");
        }

        print(matrix);


        if (isGoodKidWithoutPresent(matrix)) {
            int countLeftKids = getCount(matrix);
            System.out.println("No presents for " + countLeftKids + " nice kid/s.");
        } else {
            System.out.println("Good job, Santa! " + goodKidsWithPresents + " happy nice kid/s.");
        }

    }

    private static int getCount(String[][] matrix) {
        int count = 0;
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[r].length; c++) {
                if (matrix[r][c].equals("V")) {
                    count++;
                }
            }
        }
        return count;
    }

    private static boolean isGoodKidWithoutPresent(String[][] matrix) {
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[r].length; c++) {
                if (matrix[r][c].equals("V")) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void print(String[][] matrix) {
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[r].length; c++) {
                System.out.print(matrix[r][c] + " ");
            }
            System.out.println();
        }
    }

    private static void move(String[][] matrix, int rowMutator, int colMutator) {
        matrix[santaRow][santaCol] = "-";

        int nextRow = santaRow + rowMutator;
        int nextCol = santaCol + colMutator;

        if (isOutOfBounds(nextRow, nextCol, matrix)) {
            isOut = true;
            return;
        }

        if (matrix[nextRow][nextCol].equals("C")) {
            santaRow = nextRow;
            santaCol = nextCol;

            happySanta(matrix, -1, 0);

            happySanta(matrix, 1, 0);

            happySanta(matrix, 0, -1);

            happySanta(matrix, 0, 1);

            if (countOfPresents <= 0) {
                matrix[nextRow][nextCol] = "S";
                return;
            }
        } else if (matrix[nextRow][nextCol].equals("V")) {
            countOfPresents--;
            goodKidsWithPresents++;
        }

        matrix[nextRow][nextCol] = "S";
        santaRow = nextRow;
        santaCol = nextCol;

    }

    private static boolean isOutOfBounds(int nextRow, int nextCol, String[][] matrix) {
        return nextRow < 0 || nextCol < 0 || nextRow == matrix.length || nextCol == matrix[nextRow].length;
    }

    private static void happySanta(String[][] matrix, int rowChanger, int colChanger) {
        int row = santaRow + rowChanger;
        int col = santaCol + colChanger;

        if (matrix[row][col].equals("V") || matrix[row][col].equals("X")) {
            matrix[row][col] = "-";
            countOfPresents--;
            goodKidsWithPresents++;
        }

    }
}
