package exams.fourth;

import java.util.ArrayDeque;
import java.util.Scanner;

public class P01_FlowerWreaths {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] firstLine = scanner.nextLine().split(", ");
        String[] secondLine = scanner.nextLine().split(", ");

        ArrayDeque<Integer> lilies = new ArrayDeque<>(); //stack
        for (String num : firstLine) {
            lilies.push(Integer.parseInt(num));
        }

        ArrayDeque<Integer> roses = new ArrayDeque<>(); //queue;
        for (String num : secondLine) {
            roses.offer(Integer.parseInt(num));
        }

        int countWreaths = 0; //венци;
        int storedFlowers = 0;



        while (!lilies.isEmpty() && !roses.isEmpty()) {
            int lastLilies = lilies.peek();
            int firstRoses = roses.peek();
            int sum = lastLilies + firstRoses;

            if (sum > 15) {
                lastLilies = lastLilies- 2;
                sum = lastLilies+firstRoses;
                if (sum==15){
                    countWreaths++;
                    lilies.pop();
                    roses.poll();
                }else if (sum<15){
                    storedFlowers+=sum;
                    lilies.pop();
                    roses.poll();
                } else {
                    int toAdd = sum/15;
                    countWreaths+=toAdd;
                    lilies.pop();
                    roses.poll();
                }
            } else if (sum < 15) {
                storedFlowers += sum;
                lilies.pop();
                roses.poll();
            } else if (sum == 15) {
                countWreaths++;
                lilies.pop();
                roses.poll();
            }

        }
        if (storedFlowers>15){
            int countToAdd = storedFlowers/15;
            countWreaths+=countToAdd;
        }

        if (countWreaths>=5){
            System.out.println("You made it, you are going to the competition with " + countWreaths + " wreaths!");
        }else{
            System.out.println("You didn't make it, you need " + (5-countWreaths) + " wreaths more!");
        }
    }
}
