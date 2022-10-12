package functionalProgramming;

import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;

public class KnightsOfHonor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Consumer<String> appender = s -> System.out.println("Sir " + s);
//        Arrays.stream(scanner.nextLine().split("\\s+"))
//                .forEach(appender);
        Function<String, String> appendSir = s-> "Sir " + s;
        Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(appendSir)
                .forEach(System.out::println);
    }
}
