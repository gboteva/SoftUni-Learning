package FunctionalPrograming.ex;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class P01_ConsumerPrint {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Consumer<String> printNewLine = System.out::println;
        Arrays.stream(scanner.nextLine().split("\\s+"))
                .forEach(printNewLine);


    }
}
