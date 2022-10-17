package exams.sixth;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class P01_MagicBox {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] firstLine = scanner.nextLine().split("\\s+");
        String[] secondLine = scanner.nextLine().split("\\s+");

        ArrayDeque<Integer> firstBox = new ArrayDeque<>();
        Arrays.stream(firstLine).mapToInt(Integer::parseInt)
                .forEach(firstBox::offer);

        ArrayDeque<Integer> secondBox = new ArrayDeque<>();
        Arrays.stream(secondLine).mapToInt(Integer::parseInt)
                .forEach(secondBox::push);

        int sum = 0;
        while (!firstBox.isEmpty() && !secondBox.isEmpty()){
            int firstNum = firstBox.peek();
            int secondNum = secondBox.pop();
            int result = firstNum + secondNum;

            if (result%2==0){
               sum+=result;
               firstBox.poll();
            }else {
                firstBox.offer(secondNum);
            }
        }
        if (firstBox.isEmpty()){
            System.out.println("First magic box is empty.");
        }else {
            System.out.println("Second magic box is empty.");
        }

        if (sum>=90){
            System.out.println("Wow, your prey was epic! Value: " + sum);
        }else {
            System.out.println("Poor prey... Value: " + sum);
        }
    }
}
