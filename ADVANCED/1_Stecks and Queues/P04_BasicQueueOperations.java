package StacksAndQueues_01.ex;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Scanner;

public class P04_BasicQueueOperations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String [] input = scanner.nextLine().split("\\s+");
        int countOfferElement = Integer.parseInt(input[0]);
        int countPollElement = Integer.parseInt(input[1]);
        int elementToCheck = Integer.parseInt(input[2]);

        ArrayDeque<Integer> queue = new ArrayDeque<>();

        while (queue.size()<countOfferElement){
            queue.offer(scanner.nextInt());
        }

        for (int i = 0; i < countPollElement ; i++) {
            queue.poll();
        }

        if (queue.contains(elementToCheck)){
            System.out.println("true");
        }else {
            if (queue.isEmpty()){
                System.out.println(0);
            }else {
                System.out.println(Collections.min(queue));
            }
        }
    }
}
