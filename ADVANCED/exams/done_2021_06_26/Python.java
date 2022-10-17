package exams.june_2021;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Python {
    private static int pythonRow;
    private static int pythonCol;
    private static boolean gameOver = false;
    private static int pythonLength = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());

        List<String> commands = Arrays.stream(scanner.nextLine().split(", ")).collect(Collectors.toList());

        String[][] matrix = new String[size][size];
        for (int r = 0; r < size; r++) {
            String[] row = scanner.nextLine().split("\\s+");
            matrix[r] = row;
            for (int i = 0; i < row.length; i++) {
                if (row[i].equals("s")) {
                    pythonRow = r;
                    pythonCol = i;
                    break;
                }
            }
        }


        for (String command : commands) {
            switch (command) {
                case "up":
                    move(matrix, -1, 0);
                    break;
                case "down":
                    move(matrix, 1, 0);
                    break;
                case "right":
                    move(matrix, 0, 1);
                    break;
                case "left":
                    move(matrix, 0, -1);
                    break;
            }

            if (gameOver) {
                break;
            }

            //return program when python eat all food
            if (!isAnyFoodInTheField(matrix)){
                break;
            }
        }

        if (gameOver) {
            System.out.println("You lose! Killed by an enemy!");
        } else if (!isAnyFoodInTheField(matrix)) {
            System.out.println("You win! Final python length is " + pythonLength);
        }else  {
            int leftFood = getLeftFood(matrix);
            System.out.println("You lose! There is still " + leftFood + " food to be eaten.");
        }


    }

    private static int getLeftFood(String[][] matrix) {
        int count = 0;
        for (String[] row : matrix) {
            for (String symbol : row) {
                if (symbol.equals("f")) {
                    count++;
                }
            }
        }
        return count;
    }

    private static boolean isAnyFoodInTheField(String[][] matrix) {
        for (String[] row : matrix) {
            for (String symbol : row) {
                if (symbol.equals("f")) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void move(String[][] matrix, int rowMutator, int colMutator) {
        int nextRow = pythonRow + rowMutator;
        int nextCol = pythonCol + colMutator;

        if (isOutOfField(matrix, nextRow, nextCol)) {
            if (nextRow < 0 || nextRow >= matrix.length) {
                nextRow = nextRow < 0 ? matrix.length - 1 : 0;
            } else {
                nextCol = nextCol < 0 ? matrix[nextRow].length - 1 : 0;
            }
        }

        if (matrix[nextRow][nextCol].equals("f")) {
            matrix[nextRow][nextCol] = "*";
            pythonLength++;

        } else if (matrix[nextRow][nextCol].equals("e")) {
            gameOver = true;
        }

        pythonRow = nextRow;
        pythonCol = nextCol;

    }

    private static boolean isOutOfField(String[][] matrix, int nextRow, int nextCol) {
        return nextRow < 0 || nextRow >= matrix.length || nextCol < 0 || nextCol >= matrix[nextRow].length;
    }
}

