package exams.december2021;

import java.util.Scanner;

public class ThroneConquering {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int health = Integer.parseInt(scanner.nextLine());
        int matrixRows = Integer.parseInt(scanner.nextLine());

        String[][] matrix = new String[matrixRows][];
        fillMatrix(matrix,scanner);
        int matrixCols = matrix[0].length;

        int[] parisPlace = getPlace(matrix);
        int row = parisPlace[0];
        int col = parisPlace[1];

        boolean parisIsDead = false;

        while (!parisIsDead){
            String[] tokens = scanner.nextLine().split("\\s+");
            String direction = tokens[0];

            int spartanRow = Integer.parseInt(tokens[1]);
            int spartanCol = Integer.parseInt(tokens[2]);
            matrix[spartanRow][spartanCol] = "S";

            matrix[row][col] = "-";

            switch (direction){
                case "up":
                    row--;
                    break;
                case "down":
                    row++;
                    break;
                case "right":
                    col++;
                    break;
                case "left":
                    col--;
                    break;
            }

            health--;
            if (health<=0){
                parisIsDead = true;
                matrix[row][col] = "X";
                break;
            }

            if (isInTown(matrixRows, matrixCols, row, col)){
                if (matrix[row][col].equals("S")){
                    health-=2;
                    if (health<=0){
                        matrix[row][col] = "X";
                        parisIsDead = true;
                    }else {
                        matrix[row][col] = "P";
                    }
                }else if (matrix[row][col].equals("H")){
                    matrix[row][col] = "-";
                    break;
                }else {
                    matrix[row][col] = "P";
                }

            }else {
                parisPlace = goBack(row, col, direction);
                row = parisPlace[0];
                col = parisPlace[1];
                matrix[row][col] = "P";
            }

        }
        if (parisIsDead){
            System.out.printf("Paris died at %d;%d.%n", row, col);
        }else {
            System.out.printf("Paris has successfully abducted Helen! Energy left: %d%n", health);
        }

        printMatrix(matrix);

    }

    private static void printMatrix(String[][] matrix) {
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[r].length; c++) {
                System.out.print(matrix[r][c]);
            }
            System.out.println();
        }
    }

    private static int[] goBack(int row, int col, String direction) {
        int[] coordinates = new int[2];
        switch (direction){
            case "up":
                row++;
                break;
            case "down":
                row--;
                break;
            case "right":
                col--;
                break;
            case "left":
                col++;
                break;
        }
        coordinates[0] = row;
        coordinates[1] = col;
        return coordinates;
    }

    private static boolean isInTown(int matrixRows, int matrixCols, int row, int col) {
        return row>=0 && col>=0 && row<matrixRows && col<matrixCols;
    }


    private static int[] getPlace(String[][] matrix) {
        int[] coordinates = new int[2];
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col].equals("P")){
                    coordinates[0] = row;
                    coordinates[1] = col;
                }
            }
        }
        return coordinates;
    }

    private static void fillMatrix(String[][] matrix, Scanner scanner) {
        for (int row = 0; row < matrix.length; row++) {
            String[] arr = scanner.nextLine().split("");
            matrix[row] = arr;
        }
    }
}
