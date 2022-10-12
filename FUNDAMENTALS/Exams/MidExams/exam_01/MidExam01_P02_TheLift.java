package FundamentalsCourse.midExamMid.exam_01;

import java.util.Arrays;
import java.util.Scanner;

public class MidExam01_P02_TheLift {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int waitingPeople = Integer.parseInt(scanner.nextLine());
        int[] wagonsState = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();


        for (int i = 0; i < wagonsState.length; i++) {
            if (wagonsState[i] == 0) {
                if (waitingPeople >= 4) {
                    waitingPeople -= 4;
                    wagonsState[i] = 4;
                } else {
                    wagonsState[i] = waitingPeople;
                    waitingPeople -= wagonsState[i];
                }
            } else {
                if (waitingPeople + wagonsState[i] >= 4) {
                    waitingPeople -= 4 - wagonsState[i];
                    wagonsState[i] = 4;
                } else {
                    wagonsState[i] = waitingPeople + wagonsState[i];
                    waitingPeople = 0;
                }
            }
        }
        int countWagons = wagonsState.length;
        int usedSeat = Arrays.stream(wagonsState).sum();
        if (waitingPeople > 0 && usedSeat == countWagons * 4) {
            System.out.printf("There isn't enough space! %d people in a queue!%n", waitingPeople);
            for (int element : wagonsState) {
                System.out.print(element + " ");
            }
        } else if (waitingPeople == 0 && usedSeat < countWagons * 4) {
            System.out.println("The lift has empty spots!");
            for (int element : wagonsState) {
                System.out.print(element + " ");
            }
        } else {
            for (int element : wagonsState) {
                System.out.print(element + " ");
            }
        }
    }
}

