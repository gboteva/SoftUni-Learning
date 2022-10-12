package stacksAndQueues;

import java.util.ArrayDeque;
import java.util.Scanner;

public class SimpleTextEditor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfCommand = Integer.parseInt(scanner.nextLine());

        StringBuilder builder = new StringBuilder();
        ArrayDeque<String> stack = new ArrayDeque<>();

        for (int i = 0; i < numberOfCommand; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");
            String commandType = tokens[0];

            switch (commandType) {
                case "1":
                    stack.push(builder.toString());
                    String toAppend = tokens[1];
                    builder.append(toAppend);
                    break;
                case "2":
                    stack.push(builder.toString());

                    int countToErase = Integer.parseInt(tokens[1]);

                    if (countToErase<=builder.toString().length()){
                        builder.delete(builder.toString().length() - countToErase, builder.toString().length());
                    }else {
                        builder = new StringBuilder();
                        stack.push(builder.toString());
                    }
                    break;
                case "3":
                    int indexOfElement = Integer.parseInt(tokens[1]) - 1;
                    if (indexOfElement>=0 && indexOfElement < builder.toString().length()){
                        System.out.println(builder.charAt(indexOfElement));
                    }
                    break;
                case "4":
                    if ((!stack.isEmpty())) {
                        builder = new StringBuilder(stack.pop());
                    }

                    break;

            }

        }


    }
}
