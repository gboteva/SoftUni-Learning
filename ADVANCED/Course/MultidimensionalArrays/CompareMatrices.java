package MultidimensionalArrays;

import java.util.Arrays;
import java.util.Scanner;

public class CompareMatrices {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] dimensionsOfFirstMatrix = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();

        int[][] firstMatrix = fillMatrix(scanner, dimensionsOfFirstMatrix);


        int[] dimensionsOfSecondMatrix = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();

        int[][] secondMatrix = fillMatrix(scanner, dimensionsOfSecondMatrix);

        boolean isEqual = compareMatrix(firstMatrix, secondMatrix);
        if (isEqual){
            System.out.println("equal");
        }else {
            System.out.println("not equal");
        }

    }

    private static boolean compareMatrix(int[][] firstMatrix, int[][] secondMatrix) {
        //имат ли еднакъв брой редове
        if (firstMatrix.length != secondMatrix.length) {
            return false;
        }
        for (int row = 0; row < firstMatrix.length; row++) {
            //дължината на реда на едната матрица равен ли е на дължината на съответния ред на другата
            //без тази проверка може да опитам да извикам индекс, който не съществува
            if (firstMatrix[row].length != secondMatrix[row].length){
                return false;
            }
            for (int col = 0; col < firstMatrix[row].length; col++) {
                if (firstMatrix[row][col] != secondMatrix[row][col]){
                    return false;
                }
            }
        }
        return true;

    }

    private static int[][] fillMatrix(Scanner scanner, int[] dimensionsOfFirstMatrix) {
        int rows = dimensionsOfFirstMatrix[0];
        int cols = dimensionsOfFirstMatrix[1];

        int[][] matrix = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            matrix[i] = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt).toArray();
        }
        return matrix;
    }


}
