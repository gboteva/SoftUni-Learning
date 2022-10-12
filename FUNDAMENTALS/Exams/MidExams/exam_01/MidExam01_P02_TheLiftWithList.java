package FundamentalsCourse.midExamMid.exam_01;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MidExam01_P02_TheLiftWithList {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int waitingPeople = Integer.parseInt(scanner.nextLine());
        List<Integer> wagons = Arrays.stream(scanner.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());

        for (int i = 0; i < wagons.size(); i++) {
            if (wagons.get(i) == 0) {
                if (waitingPeople > 4) {
                    wagons.add(i, 4);
                    wagons.remove(i + 1);
                    waitingPeople -= 4;
                } else {
                    wagons.add(i, waitingPeople);
                    wagons.remove(i + 1);
                    waitingPeople = 0;
                }

            } else if (wagons.get(i) > 0) {
                if (waitingPeople + wagons.get(i) >= 4) {
                    waitingPeople -= 4 - wagons.get(i);
                    wagons.add(i, 4);
                    wagons.remove(i + 1);
                } else {
                    wagons.add(i, waitingPeople+wagons.get(i));
                    wagons.remove(i + 1);
                    waitingPeople = 0;
                }
            }

        }
        int allSeats = wagons.size()*4;
        int usedSeats = 0;
        for (int j = 0; j <wagons.size() ; j++) {
            usedSeats+=wagons.get(j);
        }
        if (waitingPeople==0 && allSeats>usedSeats){
            System.out.println("The lift has empty spots!");
            for ( int element : wagons ){
                System.out.print(element+" ");
            }
        }else if (waitingPeople>0 && allSeats<=usedSeats){
            System.out.printf("There isn't enough space! %d people in a queue!%n",waitingPeople);
            for ( int element : wagons ){
                System.out.print(element+" ");
            }
        }else if (allSeats==usedSeats && waitingPeople==0){
            for ( int element : wagons ){
                System.out.print(element+" ");
            }
        }
    }
}
