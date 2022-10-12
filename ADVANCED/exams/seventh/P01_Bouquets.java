package exams.seventh;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class P01_Bouquets {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] firstLine = scanner.nextLine().split(", ");
        String[] secondLine = scanner.nextLine().split(", ");
        ArrayDeque<Integer> tulips = new ArrayDeque<>();

        Arrays.stream(firstLine).mapToInt(Integer::parseInt)
                .forEach(tulips::push);

        ArrayDeque<Integer> daffodils = new ArrayDeque<>();
        Arrays.stream(secondLine).mapToInt(Integer::parseInt)
                .forEach(daffodils::offer);

        int countBouquets = 0;
        int stored = 0;

        while (!tulips.isEmpty() && !daffodils.isEmpty()) {
            int firstFlower = tulips.pop();
            int secondFlower = daffodils.poll();
            int result = firstFlower + secondFlower;

            if (result == 15) {
                countBouquets++;

            } else if (result > 15) {
                firstFlower = firstFlower- 2;
                result = firstFlower+secondFlower;
                if (result==15){
                    countBouquets++;
                }else if (result<15){
                    stored+=result;
                } else {
                    int toAdd = result/15;
                    countBouquets+=toAdd;
                }

            } else {
                stored+=result;
            }
        }

        int countToAdd = stored/15;
        countBouquets+=countToAdd;

        if (countBouquets>=5){
            System.out.println("You made it! You go to the competition with "
            + countBouquets + " bouquets!");
        }else {
            System.out.printf("You failed... You need more %d bouquets.", 5-countBouquets);
        }

    }
}

