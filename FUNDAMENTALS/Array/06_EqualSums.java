package Array_Ex;

import java.util.Arrays;
import java.util.Scanner;

public class EqualSums_06 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] input = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        // 10 5 5 (20) 99 (20)3 4 2 5 1 1 4
        boolean equalsSum = false;
        for (int i = 0; i < input.length; i++) {
            if (input.length == 1) {
                System.out.println("0");
                break;
            }
            int leftSum = 0;
            int rightSum = 0;

            for (int j = 0; j < i; j++) {
                leftSum += input[j];
            }
            for (int j = i + 1; j < input.length; j++) {
                rightSum += input[j];
            }
            if (leftSum == rightSum) {
                System.out.println(i);
                equalsSum=true;
            }

        }
        if (!equalsSum && input.length!=1){
            System.out.println("no");
        }
    }
}
