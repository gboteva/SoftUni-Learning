package multidimensionalArrays_02.ex;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class P06_StringMatrixRotation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        String word = scanner.nextLine();
        List<String> wordsList = new ArrayList<>();
        int maxLenght = Integer.MIN_VALUE;
        while (!word.equals("END")){
            wordsList.add(word);
            if (word.length()>maxLenght){
                maxLenght = word.length();
            }
            word = scanner.nextLine();
        }
        int rows = wordsList.size();
        int cols = maxLenght;
        String [][] matrix = new String[rows][cols];
        fillMatrix (matrix, wordsList);

        int degrees = getDegreeFromCommand(command);
        while (degrees >= 360){
            degrees = degrees % 360;
        }

        String [][] flipMatrix = new String[cols][rows];
        if (degrees == 90) {
            fillVerticalMatrix(flipMatrix,matrix,rows, cols);
        } else if (degrees == 0) {
            flipMatrix=matrix;
        } else if (degrees == 180) {
            fillVerticalMatrix(flipMatrix,matrix,rows, cols);
            cols = rows;
            rows = flipMatrix.length;
            matrix=flipMatrix;
            flipMatrix = new String[cols][rows];
            fillHorizontalMatrix(flipMatrix,matrix,rows, cols);
        } else if (degrees == 270) {
            fillVerticalMatrix(flipMatrix,matrix,rows, cols); //7 x 3
            cols = rows; //3
            rows = flipMatrix.length; // 7
            matrix=flipMatrix;
            flipMatrix = new String[cols][rows];

            fillHorizontalMatrix(flipMatrix,matrix,rows, cols); // 3 h 7
            cols = rows;
            rows = flipMatrix.length;
            matrix=flipMatrix;
            flipMatrix = new String[cols][rows];

            fillVerticalMatrix(flipMatrix,matrix,rows, cols);
        }

        for (int i = 0; i < flipMatrix.length; i++) {
            for (int j = 0; j < flipMatrix[i].length; j++) {
               if (flipMatrix[i][j] != null){
                   System.out.print(flipMatrix[i][j] );
               } else {
                   System.out.print(" ");
               }
            }
            System.out.println();
        }
    }


    private static void fillHorizontalMatrix(String[][] flipMatrix, String[][] matrix, int rows, int cols) {
        for (int row = rows-1; row >=0 ; row--) {
            for (int col = 0; col < cols; col++) {
                String currentChar = matrix[row][col];
                flipMatrix[col][rows-1-row] = matrix[row][col];
            }
        }

    }

    private static void fillVerticalMatrix(String[][] flipMatrix, String[][] matrix, int rows, int cols) {
        for (int row = rows-1; row >=0 ; row--) { //обхожда редове от последния към първия
            for (int col = 0; col < cols; col++) {    // и колони от първата към последната
               flipMatrix[col][rows-1-row] = matrix[row][col];
            }
        }

    }

    private static void fillMatrix(String[][] matrix, List<String> wordsList) {

        for (int row = 0; row < matrix.length; row++) {
            int wordLenght = wordsList.get(row).length();
            for (int col = 0; col < wordLenght; col++) {
                matrix[row][col] = "" + wordsList.get(row).charAt(col);
            }

        }
    }

    private static int getDegreeFromCommand(String command) {
        int degree = 0;
        String regex = "[A-z]+\\({1}(?<degree>\\d+)\\){1}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(command);

        if (matcher.find()){
            degree =Integer.parseInt(matcher.group("degree"));
        }
        return degree;
    }
}
