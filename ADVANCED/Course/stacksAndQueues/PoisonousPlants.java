package stacksAndQueues;

import java.util.*;
import java.util.stream.Collectors;

public class PoisonousPlants {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int countPlants = Integer.parseInt(scanner.nextLine());
        List<Integer> initialPlants = Arrays.stream(scanner.nextLine().split("\\s+")).map(Integer::parseInt).collect(Collectors.toList());

        boolean isDone = false;
        int day = 0;

        while (!isDone){
            for (int i = initialPlants.size() - 1; i > 0; i--) {
                if (initialPlants.get(i) > initialPlants.get(i - 1)) {
                    initialPlants.remove(i);
                }
            }

            isDone = isThereWeakerPlants(initialPlants);
            day++;
        }
        System.out.println(day);

    }


    private static boolean isThereWeakerPlants(List<Integer> initialPlants) {

        for (int i = 0; i < initialPlants.size() - 1; i++) {
            if (initialPlants.get(i + 1) > initialPlants.get(i)) {
                return false;
            }
        }
        return true;
    }
}
