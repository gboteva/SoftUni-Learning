package functionalProgramming;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AppliedArithmetics {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> numbers = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt).collect(Collectors.toList());

        String command = scanner.nextLine();

        while (!command.equals("end")) {
            Function<Integer, Integer> function = n -> n;

            switch (command) {
                case "add":
                    function = num -> num + 1;
                    break;
                case "multiply":
                    function = num -> num * 2;
                    break;
                case "subtract":
                    function = num -> num - 1;
                    break;
                case "print":
                    Consumer<Integer> print = n -> System.out.print(n + " ");
                    numbers.forEach(print);
                    System.out.println();
                    break;
            }

            numbers = numbers.stream().map(function).collect(Collectors.toList());

            command = scanner.nextLine();
        }

    }
}
