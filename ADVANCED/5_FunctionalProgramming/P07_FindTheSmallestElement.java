package FunctionalPrograming.ex;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class P07_FindTheSmallestElement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> numbers = Arrays.stream(scanner.nextLine().split("\\s+")).map(Integer::parseInt).collect(Collectors.toList());

//        Function<List<Integer>, Integer> lastSmallerElement = list-> list.lastIndexOf(Collections.min(list));
//        System.out.println(lastSmallerElement.apply(numbers));
        Consumer<List<Integer>> getIndexOfLastSmallerElement = list -> System.out.println(list.lastIndexOf(Collections.min(list)));
        getIndexOfLastSmallerElement.accept(numbers);
    }
}
