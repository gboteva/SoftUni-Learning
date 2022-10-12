package stacksAndQueues;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class MathPotato {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] children = scanner.nextLine().split("\\s+");
        int count = Integer.parseInt(scanner.nextLine());

        ArrayDeque<String> queue = new ArrayDeque<>();
        Arrays.stream(children).forEach(queue::offer);
        int circleCounter = 1;

        while (queue.size() > 1) {
            for (int i = 1; i <= count-1; i++) {
                queue.offer(queue.poll());
            }
            if (!isPrime(circleCounter, queue)) {
                System.out.println("Removed " + queue.poll());
            }else {
                System.out.println("Prime " + queue.peek());
            }

            circleCounter++;
        }
        System.out.println("Last is " + queue.poll());
    }

    private static boolean isPrime(int circleCounter, ArrayDeque<String> queue) {
        int countDivisor = 0;

        for (int i = 1; i <= circleCounter; i++) {
            if (circleCounter % i == 0) {
                countDivisor++;
            }
        }

        if (countDivisor==2){
            return true;
        }
        return false;
    }
}
