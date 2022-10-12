package stacksAndQueues;

import java.util.ArrayDeque;
import java.util.Scanner;

public class MaximumElement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int countCommands = Integer.parseInt(scanner.nextLine());

        ArrayDeque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < countCommands; i++) {
            String[] commandTokens = scanner.nextLine().split("\\s+");

            switch (commandTokens[0]){
                case "1":
                    stack.push(Integer.parseInt(commandTokens[1]));
                    break;
                case "2":
                    stack.pop();
                    break;
                case "3":
                    if (!stack.isEmpty()){
                        System.out.println(stack.stream().mapToInt(n->n)
                                .max().getAsInt());
                    }else {
                        System.out.println(0);
                    }
                    break;
            }

        }
    }
}
