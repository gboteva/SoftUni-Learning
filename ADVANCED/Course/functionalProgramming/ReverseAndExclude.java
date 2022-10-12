package functionalProgramming;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ReverseAndExclude {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> numbers = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt).collect(Collectors.toList());
        Collections.reverse(numbers);

        int divisibleBy = Integer.parseInt(scanner.nextLine());
        Predicate<Integer> divisibleByNumber = e -> e % divisibleBy != 0;

        numbers.stream().filter(divisibleByNumber)
                .forEach(e -> System.out.print(e + " "));
    }

}

