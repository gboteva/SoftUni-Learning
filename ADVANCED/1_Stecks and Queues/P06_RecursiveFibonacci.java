package StacksAndQueues_01.ex;

import java.util.ArrayDeque;
import java.util.Scanner;

public class P06_RecursiveFibonacci {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = Integer.parseInt(scanner.nextLine());
        ArrayDeque<Long> fibonacci = new ArrayDeque<>();
        fibonacci.push(1L);
        fibonacci.push(1L);

        //1, 1, 2, 3, 5, 8, 13, 21, 34
        for (int i = 0; i < number-1; i++) {
           long first = fibonacci.pop();
           long second = fibonacci.pop();
           long nextNumber = first+ second;
           fibonacci.push(second);
           fibonacci.push(first);
           fibonacci.push(nextNumber);
        }

            System.out.println(fibonacci.peek());


    }
}
