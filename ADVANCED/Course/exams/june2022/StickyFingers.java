package exams.june2022;


import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class StickyFingers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int dillingerFieldSize = Integer.parseInt(scanner.nextLine());
        List<String> commands = Arrays.stream(scanner.nextLine().split(",")).collect(Collectors.toList());

        String[][] matrix = new String[dillingerFieldSize][dillingerFieldSize];
        fillMatrix(matrix, dillingerFieldSize, scanner);

        int[] coordinates = getDillingerCoordinates(matrix);
        int row = coordinates[0];
        int col = coordinates[1];

        int stolenMoney = 0;
        boolean isInJail = false;
        for (String command : commands) {
            matrix[row][col] = "+";

            boolean isOutside = false;
            switch (command) {
                case "up":
                    if (row - 1 == -1) {
                        isOutside = true;
                    } else {
                        row--;
                    }
                    break;
                case "down":
                    if (row + 1 == dillingerFieldSize) {
                        isOutside = true;
                    } else {
                        row++;
                    }
                    break;
                case "right":
                    if (col + 1 == dillingerFieldSize) {
                        isOutside = true;
                    } else {
                        col++;
                    }
                    break;
                case "left":
                    if (col - 1 == -1) {
                        isOutside = true;
                    } else {
                        col--;
                    }
                    break;
            }

            if (isOutside) {
                matrix[row][col] = "D";
                System.out.println("You cannot leave the town, there is police outside!");
            } else {
                if (matrix[row][col].equals("$")) {
                    //house
                    matrix[row][col] = "D";
                    int money = row * col;
                    stolenMoney += money;
                    System.out.printf("You successfully stole %d$.%n", money);

                } else if (matrix[row][col].equals("P")) {
                    //police
                    matrix[row][col] = "#";
                    isInJail = true;
                    break;
                }else {
                    matrix[row][col] = "D";
                }

            }

        }

        if (!isInJail){
            System.out.printf("Your last theft has finished successfully with %d$ in your pocket.%n", stolenMoney);
        }else {
            System.out.printf("You got caught with %d$, and you are going to jail.%n", stolenMoney);
        }
        printMatrix(matrix);
    }

    private static void printMatrix(String[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static int[] getDillingerCoordinates(String[][] matrix) {
        int[] coordinates = new int[2];
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col].equals("D")) {
                    coordinates[0] = row;
                    coordinates[1] = col;
                    break;
                }
            }
        }
        return coordinates;
    }

    private static void fillMatrix(String[][] matrix, int rows, Scanner scanner) {
        for (int row = 0; row < rows; row++) {
            String[] currentRow = scanner.nextLine().split(" ");
            for (int col = 0; col < matrix[row].length; col++) {
                matrix[row][col] = currentRow[col];
            }
        }
    }
}
