package exams.april2022;

import java.util.*;
import java.util.stream.Collectors;

public class Blacksmith {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> firstInput = Arrays.stream(scanner.nextLine().split("\\s+")).map(Integer::parseInt)
                .collect(Collectors.toList());
        List<Integer> secondInput = Arrays.stream(scanner.nextLine().split("\\s+")).map(Integer::parseInt)
                .collect(Collectors.toList());

        ArrayDeque<Integer> steel = new ArrayDeque<>();
        firstInput.forEach(steel::offer);

        ArrayDeque<Integer> carbon = new ArrayDeque<>();
        secondInput.forEach(carbon::push);
        Map<String, Integer> swords = new TreeMap<>();
        swords.put("Gladius", 0);
        swords.put("Shamshir", 0);
        swords.put("Katana", 0);
        swords.put("Sabre", 0);

        while (!steel.isEmpty() && !carbon.isEmpty()) {
            int currentSteel = steel.poll();
            int currentCarbon = carbon.pop();
            int result = currentCarbon + currentSteel;

            switch (result) {
                case 70:
                    swords.put("Gladius", swords.get("Gladius") + 1);
                    break;
                case 80:
                    swords.put("Shamshir", swords.get("Shamshir") + 1);
                    break;
                case 90:
                    swords.put("Katana", swords.get("Katana") + 1);
                    break;
                case 110:
                    swords.put("Sabre", swords.get("Sabre") + 1);
                    break;
                default:
                    currentCarbon += 5;
                    carbon.push(currentCarbon);
                    break;
            }
        }

        int allSwords = swords.values().stream().mapToInt(e -> e).sum();

        if (allSwords == 0) {
            System.out.println("You did not have enough resources to forge a sword.");
        }else {
            System.out.printf("You have forged %d swords.%n", allSwords);
        }

        if (steel.isEmpty()){
            System.out.println("Steel left: none");
        }else {
            List<String> stringSteel = new ArrayList<>();
            carbon.forEach(i->stringSteel.add(String.valueOf(i)));

            System.out.println("Steel left: " + String.join(", ", stringSteel));
        }

        if (carbon.isEmpty()){
            System.out.println("Carbon left: none");
        }else {
            List<String> stringCarbon = new ArrayList<>();
            carbon.forEach(i->stringCarbon.add(String.valueOf(i)));

            System.out.println("Carbon left: " + String.join(", ", stringCarbon));
        }

        for (Map.Entry<String, Integer> entry : swords.entrySet()) {
            String type = entry.getKey();
            int count = entry.getValue();

            if (count>0){
                System.out.println(type + ": " + count);
            }
        }


    }
}
