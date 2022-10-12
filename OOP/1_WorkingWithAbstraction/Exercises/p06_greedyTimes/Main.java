
package Exercises.p06_greedyTimes;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long bagCapacity = Long.parseLong(scanner.nextLine());
        String[] items = scanner.nextLine().split("\\s+");

        Map<String, LinkedHashMap<String, Long>> bag = new LinkedHashMap<>();

        for (int i = 0; i < items.length; i += 2) {
            String itemName = items[i];
            long quantity = Long.parseLong(items[i + 1]);

            String validItem = "";

            if (itemName.length() == 3) {
                validItem = "Cash";
            } else if (itemName.toLowerCase().endsWith("gem")&& itemName.length()>=4) {
                validItem = "Gem";
            } else if (itemName.equalsIgnoreCase("gold")) {
                validItem = "Gold";
            }

            if (validItem.equals("") || bagCapacity < bag.values().stream()
                                        .map(Map::values).flatMap(Collection::stream)
                                         .mapToLong(e -> e).sum() + quantity) {
                continue;
            }

            switch (validItem) {
                case "Gem":
                    if (!bag.containsKey(validItem)) {
                        if (bag.containsKey("Gold")) {
                            long currentGoldQuantity = bag.get("Gold").values().stream().mapToLong(e -> e).sum();
                            if (quantity > currentGoldQuantity) {
                                continue;
                            }
                        } else {
                            continue;
                        }
                    } else if (bag.get(validItem).values().stream().mapToLong(e -> e).sum() + quantity > bag.get("Gold").values().stream().mapToLong(e -> e).sum()) {
                        continue;
                    }
                    break;
                case "Cash":
                    if (!bag.containsKey(validItem)) {
                        if (bag.containsKey("Gem")) {
                            long currentQuantityOfGold = bag.get("Gold").values().stream().mapToLong(e -> e).sum();
                            if (quantity > currentQuantityOfGold) {
                                continue;
                            }
                        } else {
                            continue;
                        }
                    } else if (bag.get(validItem).values().stream().mapToLong(e -> e).sum() + quantity > bag.get("Gem").values().stream().mapToLong(e -> e).sum()) {
                        continue;
                    }
                    break;
            }

            if (!bag.containsKey(validItem)) {
                bag.put((validItem), new LinkedHashMap<>());
            }

            if (!bag.get(validItem).containsKey(itemName)) {
                bag.get(validItem).put(itemName, 0L);
            }

            long currentQuantity = bag.get(validItem).get(itemName);
            bag.get(validItem).put(itemName, currentQuantity + quantity);

        }



        for (Map.Entry<String, LinkedHashMap<String, Long>> entry : bag.entrySet()) {
            long sumValues = entry.getValue().values().stream().mapToLong(l -> l).sum();

            System.out.println(String.format("<%s> $%s", entry.getKey(), sumValues));

            entry.getValue().entrySet().stream().sorted((e1, e2) -> e2.getKey().compareTo(e1.getKey())).forEach(i -> System.out.println("##" + i.getKey() + " - " + i.getValue()));

        }
    }
}