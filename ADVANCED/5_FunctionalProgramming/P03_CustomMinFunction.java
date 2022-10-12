package FunctionalPrograming.ex;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class P03_CustomMinFunction {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> numbers =  Arrays.stream(scanner.nextLine().split("\\s+")).map(Integer::parseInt).collect(Collectors.toList());
        Function<List<Integer>, Integer> minValue = list->Collections.min(list);
        System.out.println(minValue.apply(numbers));

//        Consumer<List<Integer>> printMinNumber = list-> System.out.println(Collections.min(list));
//        printMinNumber.accept(numbers);

    }
}
