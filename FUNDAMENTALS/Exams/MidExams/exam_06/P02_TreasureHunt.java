package FundamentalsCourse.midExamMid.exam_06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class P02_TreasureHunt {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> initialLoot = Arrays.stream(scanner.nextLine().split("\\|")).collect(Collectors.toList());
        String command = scanner.nextLine();

        while (!command.equals("Yohoho!")) {
            String action = command.split(" ")[0];

            switch (action) {
                case "Loot":
                    String[] itemsArray = command.split(" ");
                    List<String> items = new ArrayList<>();
                    for (int i = 1; i < itemsArray.length; i++) {
                        items.add(itemsArray[i]);
                    }

                    for (int i = 0; i < items.size(); i++) {
                        if (!initialLoot.contains(items.get(i))) {
                            initialLoot.add(0, items.get(i));
                        }
                    }
                    break;
                case "Drop":
                    int index = Integer.parseInt(command.split(" ")[1]);
                    if (index >= 0 && index < initialLoot.size()) {
                        String dropItem = initialLoot.get(index);
                        initialLoot.remove(index);
                        initialLoot.add(dropItem);
                    }
                    break;
                case "Steal":
                    int count = Integer.parseInt(command.split(" ")[1]);
                    List<String> stolenItems = new ArrayList<>();
                    if (initialLoot.size() >= count) {
                        int size = initialLoot.size();
                        for (int i = size - count; i < size; i++) {
                            stolenItems.add(initialLoot.get(i));
                        }
                        for (int i = size - 1; i >= size - count; i--) {
                            initialLoot.remove(initialLoot.size() - 1);
                        }

                    } else {

                        for (int i = 0; i < initialLoot.size(); i++) {
                            stolenItems.add(initialLoot.get(i));
                            initialLoot.remove(0);
                            i=-1;
                        }
                    }
                    System.out.println(stolenItems.toString().replaceAll("[\\[\\]]", ""));
                    break;
            }

            command = scanner.nextLine();
        }
        int sum = 0;
        for (int i = 0; i < initialLoot.size(); i++) {
            sum += initialLoot.get(i).length();
        }
        double average = sum * 1.0 / initialLoot.size();

        if (initialLoot.isEmpty()) {
            System.out.println("Failed treasure hunt.");
        } else {
            System.out.printf("Average treasure gain: %.2f pirate credits.%n", average);
        }

    }
}
