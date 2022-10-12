package MultidimensionalArrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Crossfire {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] dimensions = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();

        List<List<Integer>> matrix = new ArrayList<>();
        fillMatrix(dimensions, matrix);

        String command = scanner.nextLine();
        while (!command.equals("Nuke it from orbit")) {
            int[] tokens = Arrays.stream(command.split("\\s+"))
                    .mapToInt(Integer::parseInt).toArray();
            int row = tokens[0];
            int col = tokens[1];
            int radius = tokens[2];


            for (int i = row - radius; i <= row + radius; i++) {
                if (isInRange(i, col, matrix) && i != row){
                    matrix.get(i).remove(col);
                }
            }

            for (int i = col + radius; i >= col - radius; i--) {
                if (isInRange(row, i, matrix)){
                    matrix.get(row).remove(i);
                }
            }

            matrix.removeIf(List::isEmpty);
            command = scanner.nextLine();
        }
        print(matrix);


    }

    private static boolean isInRange(int row, int col, List<List<Integer>> matrix) {
        return row >= 0 && row < matrix.size() && col >= 0 && col < matrix.get(row).size();
    }


    private static void print(List<List<Integer>> matrix) {
        for (List<Integer> list : matrix) {
            for (Integer num : list) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }


    private static void fillMatrix(int[] dimensions, List<List<Integer>> matrix) {
        int countOfList = dimensions[0];
        int lengthOfList = dimensions[1];

        int initialValue = 1;
        for (int row = 0; row < countOfList; row++) {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < lengthOfList; i++) {
                list.add(initialValue++);
            }
            matrix.add(list);
        }
    }
}