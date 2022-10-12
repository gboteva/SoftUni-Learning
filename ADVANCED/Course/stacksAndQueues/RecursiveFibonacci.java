package stacksAndQueues;

import java.util.ArrayDeque;
import java.util.Scanner;

public class RecursiveFibonacci {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = Integer.parseInt(scanner.nextLine());
        //System.out.println(fib(n));
        ArrayDeque<Long> stack = new ArrayDeque<>();
        stack.push(1L);
        stack.push(1L);

        if (n < 2) {
            System.out.println(1);
            return;
        }

        for (int i = 0; i < n; i++) {
            long num1 = stack.pop();
            long num2 = stack.pop();
            stack.push(num1);
            stack.push(num1+num2);
        }
        System.out.println(stack.pop());
    }

//    private static long fib(int n) {
//        if (n < 2) {
//            return 1;
//        }
//        return fib(n-1) + fib(n-2);
//    }
}
