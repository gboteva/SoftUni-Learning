package exams.secondExam;

import java.util.ArrayDeque;
import java.util.Scanner;

public class LootBox {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] arr1 = scanner.nextLine().split("\\s+");
        String[] arr2 = scanner.nextLine().split("\\s+");

        //start from the first item in the first box -> queue
        //sum with the last item in the second box ->stack

        ArrayDeque<Integer> firstBox = new ArrayDeque<>();
        for (String num : arr1){
            firstBox.offer(Integer.parseInt(num));
        }

        ArrayDeque<Integer> secondBox = new ArrayDeque<>();
        for (String num : arr2){
            secondBox.push(Integer.parseInt(num));
        }

        int value = 0;
        while (!firstBox.isEmpty() && !secondBox.isEmpty()){
            int firstNum = firstBox.peek();
            int secondNum = secondBox.peek();
            int sum = firstNum + secondNum;

            if (sum%2==0){
                value+=sum;
                firstBox.poll();
                secondBox.pop();
            }else {
                secondBox.pop();
                firstBox.offer(secondNum);
            }

        }
        if (firstBox.isEmpty()){
            System.out.println("First lootbox is empty");
        }else {
            System.out.println("Second lootbox is empty");
        }

        if (value>=100){
            System.out.println("Your loot was epic! Value: " + value);
        }else {
            System.out.println("Your loot was poor... Value: " + value);
        }
    }
}
