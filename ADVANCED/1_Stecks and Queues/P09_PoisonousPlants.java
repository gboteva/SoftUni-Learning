package StacksAndQueues_01.ex;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class P09_PoisonousPlants {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfPlants = Integer.parseInt(scanner.nextLine());
        List<Integer> initalPlants = Arrays.stream(scanner.nextLine().split("\\s+")).map(Integer::parseInt).collect(Collectors.toList());

        boolean isDone = false;
        int day = 0;

        while (!isDone){
            for (int i = initalPlants.size() - 1; i > 0; i--) {
                if (initalPlants.get(i) > initalPlants.get(i - 1)) {
                    initalPlants.remove(i);
                }
            }

            isDone = isThereWeekerPlants(initalPlants);
            day++;
        }
        System.out.println(day);

        }


    private static boolean isThereWeekerPlants(List<Integer> initalPlants) {

        for (int i = 0; i < initalPlants.size() - 1; i++) {
            if (initalPlants.get(i + 1) > initalPlants.get(i)) {
                return false;
            }
        }
        return true;
    }
}
