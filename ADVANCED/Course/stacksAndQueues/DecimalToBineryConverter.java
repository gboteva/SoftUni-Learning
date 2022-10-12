package stacksAndQueues;

import java.util.ArrayDeque;
import java.util.Scanner;

public class DecimalToBineryConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int decimalNumber = Integer.parseInt(scanner.nextLine());

        ArrayDeque<Integer> stack = new ArrayDeque<>();

        while (decimalNumber!=0){
            int binaryNum = decimalNumber%2;
            stack.push(binaryNum);
            decimalNumber/=2;
        }

        if (stack.isEmpty()){
            System.out.println(0);
        }else {
            for (Integer integer : stack) {
                System.out.print(stack.pop());
            }
        }


    }
}
