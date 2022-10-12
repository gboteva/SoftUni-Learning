package beforeCourse.lists_5.moreEx;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class P01Messaging {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> numbers = Arrays.stream(scanner.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        String myString = scanner.nextLine();
        StringBuilder message = new StringBuilder(myString);
        String output = "";

        for (int i = 0; i < numbers.size(); i++) {
            int currentNum = numbers.get(i);
            int indexOfText = 0;
            while (currentNum > 0) {
                indexOfText += currentNum % 10;
                currentNum /= 10;
            }
            if (indexOfText < message.length()) {
                output = output + message.charAt(indexOfText);
            } else {
                indexOfText = indexOfText - message.length();
                output = output + message.charAt(indexOfText);
            }

            message = message.deleteCharAt(indexOfText);
        }
        System.out.println(output);
    }
}
