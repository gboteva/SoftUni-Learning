package multidimensionalArrays_02.ex;

import java.util.Scanner;

public class P05_MatrixShuffling {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String [] dimensions = scanner.nextLine().split("\\s+");
        int rows = Integer.parseInt(dimensions[0]);
        int cols = Integer.parseInt(dimensions[1]);

        String [][] matrix = new String[rows][cols];
        fillMatrix(matrix, scanner);
        String command = scanner.nextLine();

        while (!command.equals("END")){
            String [] commandParts = command.split("\\s+");
            boolean validation = isValidCommand(command,rows, cols);

            if (validation){
                int row1 =Integer.parseInt(commandParts[1]);
                int col1 =Integer.parseInt(commandParts[2]);
                int row2 =Integer.parseInt(commandParts[3]);
                int col2 =Integer.parseInt(commandParts[4]);

                String firstElement = matrix[row1][col1];
                String secondElement = matrix[row2][col2];
                matrix[row1][col1] = secondElement;
                matrix[row2][col2] = firstElement;
                printMatrix(matrix);

            }else {
                System.out.println("Invalid input!");
            }


            command= scanner.nextLine();
        }



    }
    private static void printMatrix(String[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static boolean isValidCommand(String command, int rows, int cols) {
        String [] commandParts = command.split("\\s+");
        if (commandParts.length!=5){
            return false;
        }

        if (!commandParts[0].equals("swap")){
            return false;
        }

        int row1 =Integer.parseInt(commandParts[1]);
        int col1 =Integer.parseInt(commandParts[2]);
        int row2 =Integer.parseInt(commandParts[3]);
        int col2 =Integer.parseInt(commandParts[4]);

        if (row1<0 || row1>=rows || col1<0 || col1>cols-1
                || row2<0 || row2>=rows || col2<0 || col2>cols-1){
            return false;
        }
        return true;
    }

    private static void fillMatrix(String[][] matrix, Scanner scanner) {
        for (int row = 0; row < matrix.length; row++) {
            matrix[row] = scanner.nextLine().split("\\s+");
        }
    }
}
