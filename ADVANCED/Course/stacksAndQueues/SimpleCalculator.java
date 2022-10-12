package stacksAndQueues;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class SimpleCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String [] expression = scanner.nextLine().split("\\s+");

        ArrayDeque<String> queue = new ArrayDeque<>();
        Arrays.stream(expression).forEach(queue::offer);


        while (queue.size()>1){
            Integer firstNum = Integer.parseInt(queue.poll());
            String sigh = queue.poll();
            Integer secondNum = Integer.parseInt(queue.poll());

            Integer result = null;

            if (sigh.equals("+")){
                result = firstNum+secondNum;
            }else {
                result = firstNum-secondNum;
            }
            queue.offerFirst(result.toString());
        }
        System.out.println(queue.pop());

    }
}
