package before.course.methods_4.m.ex;

import java.util.Arrays;
import java.util.Scanner;

public class P04_TribonacciSequence {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = Integer.parseInt(scanner.nextLine());
        printTribonacciLine(num);
    }

    private static void printTribonacciLine(int num) {
        int[] sumArray = new int[num];
        sumArray[0] = 1;
        int sum = Arrays.stream(sumArray).sum();
        for (int i = 1; i < sumArray.length; i++) {
            if (i < 3) {
                sum = Arrays.stream(sumArray).sum();
            } else {
                sum = sumArray[i - 1] + sumArray[i - 2] + sumArray[i - 3];
            }
            sumArray[i] = sum;
        }
        for (int number : sumArray) {
            System.out.print(number + " ");
        }
    }
}


