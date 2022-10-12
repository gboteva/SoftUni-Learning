package stacksAndQueues;

import java.util.ArrayDeque;
import java.util.Scanner;

public class BasicQueueOperations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] inputNum = scanner.nextLine().split("\\s+");
        int countElementToOffer = Integer.parseInt(inputNum[0]);
        int countElementToPoll = Integer.parseInt(inputNum[1]);
        int elementToCheck = Integer.parseInt(inputNum[2]);

        String[] numbers = scanner.nextLine().split("\\s+");
        ArrayDeque<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < countElementToOffer; i++) {
            queue.offer(Integer.parseInt(numbers[i]));
        }
        for (int i = 0; i < countElementToPoll; i++) {
            queue.poll();
        }

        if (queue.contains(elementToCheck)){
            System.out.println("true");
        }else {
            if (queue.isEmpty()){
                System.out.println(0);
            }else {
                System.out.println(queue.stream().mapToInt(e -> e).min().getAsInt());
            }
        }
    }
}
