package setsAndMaps;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class SetsOfElements {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int [] countOfElements = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();

        Set<Integer> firstSet = new LinkedHashSet<>();


        while (countOfElements[0] -- >0){
            firstSet.add(Integer.parseInt(scanner.nextLine()));
        }

        Set<Integer> secondSet = new LinkedHashSet<>();
        while (countOfElements[1] -- >0){
            secondSet.add(Integer.parseInt(scanner.nextLine()));
        }

        if (firstSet.size()<secondSet.size()){
            while (firstSet.iterator().hasNext()){
                int number = firstSet.iterator().next();
                if (secondSet.contains(number)){
                    System.out.print(number + " ");
                }

                firstSet.remove(number);
            }

        }else {
            while (secondSet.iterator().hasNext()){
                int number = secondSet.iterator().next();
                if (firstSet.contains(number)){
                    System.out.print(number + " ");
                }
                secondSet.remove(number);
            }

        }


    }
}
