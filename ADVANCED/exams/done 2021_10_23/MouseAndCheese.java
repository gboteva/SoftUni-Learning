package exams.october2021;

import java.util.Scanner;

public class MouseAndCheese {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        String[][] matrix = new String[n][];
        fillMatrix(n, scanner, matrix);

        int[] mouseCoordinates = getMousePlace(matrix);
        int row = mouseCoordinates[0];
        int col = mouseCoordinates[1];

        String command = scanner.nextLine();
        boolean isInField = true;
        int countEatenCheese = 0;

        while (!command.equals("end")) {
            matrix[row][col] = "-";
            switch (command) {
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

            isInField = checkIsInField(n, row, col);

            if (!isInField) {
                break;
            }

            if (matrix[row][col].equals("c")) {
                countEatenCheese++;

            } else if (matrix[row][col].equals("B")) {
//                continue;
                matrix[row][col] = "-";
                mouseCoordinates = moveForward(row, col, command);
                row = mouseCoordinates[0];
                col = mouseCoordinates[1];
                if (matrix[row][col].equals("c")){
                    countEatenCheese++;
                }
            }

            matrix[row][col] = "M";

            command = scanner.nextLine();
        }

        if (!isInField) {
            System.out.println("Where is the mouse?");
        }
        if (countEatenCheese >= 5) {
            System.out.printf("Great job, the mouse is fed %d cheeses!%n", countEatenCheese);
        } else {
            System.out.printf("The mouse couldn't eat the cheeses, she needed %d cheeses more.%n", 5 - countEatenCheese);
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

    private static int[] moveForward(int row, int col, String command) {
        //•	There won't be a case where a bonus moves the mouse out of its territory
        switch (command) {
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
        int[] coordinate = new int[2];
        coordinate[0] = row;
        coordinate[1] = col;
        return coordinate;
    }

    private static boolean checkIsInField(int n, int row, int col) {
        return row >= 0 && row < n && col >= 0 && col < n;
    }

    private static int[] getMousePlace(String[][] matrix) {
        int[] place = new int[2];
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[r].length; c++) {
                if (matrix[r][c].equals("M")) {
                    place[0] = r;
                    place[1] = c;
                    break;
                }
            }
        }
        return place;
    }

    private static void fillMatrix(int n, Scanner scanner, String[][] matrix) {
        for (int i = 0; i < n; i++) {
            String[] row = scanner.nextLine().split("");
            matrix[i] = row;
        }
    }
}
