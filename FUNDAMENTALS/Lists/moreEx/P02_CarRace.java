package beforeCourse.lists_5.moreEx;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class P02_CarRace {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> numbers = Arrays.stream(scanner.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        double middleNum = Math.floor(numbers.size()/2.0);
        List<Integer> firstCar = numbers.subList(0, (int) middleNum);
        List<Integer> secondCar = numbers.subList((int) middleNum+1, numbers.size());
        Collections.reverse(secondCar);
        double firstSum = 0;
        double secondSum = 0;

        for (int i = 0; i < firstCar.size() ; i++) {
            if (firstCar.get(i) != 0){
                firstSum+=firstCar.get(i);
            }else {
                firstSum*=0.8;
            }
            if (secondCar.get(i) != 0){
                secondSum+=secondCar.get(i);
            }else {
                secondSum*=0.8;
            }
        }
        if (firstSum<secondSum){
            System.out.printf("The winner is left with total time: %.1f%n", firstSum);
        }else {
            System.out.printf("The winner is right with total time: %.1f%n", secondSum);
        }
    }
}
