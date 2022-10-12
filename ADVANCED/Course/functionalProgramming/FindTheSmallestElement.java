package functionalProgramming;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FindTheSmallestElement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Function<List<Integer>, Integer> smallestElement = n -> {
            Integer min = Integer.MAX_VALUE;
            for (Integer number : n) {
                if (number < min) {
                    min = number;
                }
            }
            return min;
        };
        List<Integer> number = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        int smallest = smallestElement.apply(number);

        System.out.println(number.lastIndexOf(smallest));

    }
}
