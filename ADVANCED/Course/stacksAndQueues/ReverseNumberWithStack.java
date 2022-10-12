package stacksAndQueues;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class ReverseNumberWithStack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] numbers = scanner.nextLine().split("\\s+");

        ArrayDeque<String> numberStack = new ArrayDeque<>();
        Arrays.stream(numbers).forEach(numberStack::push);

        System.out.println(String.join(" ", numberStack));
    }
}
