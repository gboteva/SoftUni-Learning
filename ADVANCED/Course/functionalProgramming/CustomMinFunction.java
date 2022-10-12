package functionalProgramming;

import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Function;

public class CustomMinFunction {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Integer[] input = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt).toArray(Integer[]::new);

        Function<Integer[], Integer> minNum = number -> {
            int minimum = Integer.MAX_VALUE;
            for (Integer num : input) {
                if (num<minimum){
                    minimum = num;
                }
            }
            return minimum;
        };
        System.out.println(minNum.apply(input));
    }
}
