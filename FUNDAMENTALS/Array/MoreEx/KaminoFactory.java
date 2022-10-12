package FundamentalsCourse.arrays_3.moreEx;

import java.util.Arrays;
import java.util.Scanner;

public class KaminoFactory_byMe {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int DNALenght = Integer.parseInt(scanner.nextLine());
        String command = scanner.nextLine();
        int bestLenght = 0;
        int bestSum = 0;
        int bestIndex = -1;
        int[] bestArray = new int[DNALenght];
        int bestCount = 0;
        int count = 0;

        while (!command.equals("Clone them!")) {
            int[] sequance = Arrays.stream(command.split("!+")).mapToInt(Integer::parseInt).toArray();
            count++;
            int currentOnceLenght = 1;
            int currentFirstIndex = -1;

            for (int i = 0; i < sequance.length - 1; i++) {
                if (sequance[i] == 1 && sequance[i + 1] == 1) {
                    currentOnceLenght++;
                    if (currentOnceLenght == 2) {
                        currentFirstIndex = i;
                    }
                }
            }

            int currentSumOfOnce = 0;
            for (int i = 0; i < sequance.length; i++) {
                if (sequance[i] == 1) {
                    currentSumOfOnce++;
                }
            }

            if (currentOnceLenght > bestLenght) {
                bestLenght = currentOnceLenght;
                bestIndex = currentFirstIndex;
                bestSum = currentSumOfOnce;
                bestArray = sequance;
                bestCount = count;
            } else {
                if (currentOnceLenght == bestLenght) {
                    if (currentFirstIndex < bestIndex) {
                        bestIndex = currentFirstIndex;
                        bestSum = currentSumOfOnce;
                        bestArray = sequance;
                        bestCount = count;
                    } else if (currentFirstIndex == bestIndex) {
                        if (currentSumOfOnce > bestSum) {
                            bestIndex = currentFirstIndex;
                            bestSum = currentSumOfOnce;
                            bestArray = sequance;
                            bestCount = count;
                        }
                    }
                }
            }

            command = scanner.nextLine();
        }

        System.out.printf("Best DNA sample %d with sum: %d.%n", bestCount, bestSum);
        for (int i = 0; i < bestArray.length; i++) {
            System.out.print(bestArray[i] + " ");
        }
    }
}
