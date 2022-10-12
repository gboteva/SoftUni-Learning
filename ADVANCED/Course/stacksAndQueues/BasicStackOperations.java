package stacksAndQueues;

import java.util.ArrayDeque;
import java.util.Scanner;

public class BasicStackOperations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] inputNum = scanner.nextLine().split("\\s+");
        int countElementToPush = Integer.parseInt(inputNum[0]);
        int countElementToPop = Integer.parseInt(inputNum[1]);
        int elementToCheck = Integer.parseInt(inputNum[2]);

        String[] numbers = scanner.nextLine().split("\\s+");
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < countElementToPush; i++) {
            stack.push(Integer.parseInt(numbers[i]));
        }
        for (int i = 0; i < countElementToPop; i++) {
            stack.pop();
        }

        if (stack.contains(elementToCheck)){
            System.out.println("true");
        }else {
            if (stack.isEmpty()){
                System.out.println(0);
            }else {
                System.out.println(stack.stream().mapToInt(e -> e).min().getAsInt());
            }
        }

    }
}
