package StacksAndQueues_01.ex;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class P02_BasicStackOperations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String [] input = scanner.nextLine().split("\\s+");
        int countPushElement = Integer.parseInt(input[0]);
        int countPopElement = Integer.parseInt(input[1]);
        int elementToCheck = Integer.parseInt(input[2]);

        ArrayDeque<Integer> stack = new ArrayDeque<>();

        while (stack.size()<countPushElement){
            stack.push(scanner.nextInt());
        }

        for (int i = 0; i < countPopElement ; i++) {
            stack.pop();
        }

        if (stack.contains(elementToCheck)){
            System.out.println("true");
        }else {
            if (stack.isEmpty()){
                System.out.println(0);
            }else {
                System.out.println(Collections.min(stack));
            }
        }
    }
}
