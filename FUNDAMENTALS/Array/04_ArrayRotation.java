package Array_Ex;

import java.util.Scanner;

public class ArrayRotation_04 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(" ");
        int countRotations = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < countRotations; i++) {
            String firstSymbol = input[0];
            for (int j = 0; j < input.length - 1; j++) {
                input[j] = input[j + 1];
            }
            input[input.length - 1] = firstSymbol;
        }
        for (String element : input) {
            System.out.print(element + " ");
        }
    }
}
