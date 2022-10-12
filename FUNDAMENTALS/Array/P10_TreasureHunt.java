package FundamentalsCourse.arrays_3.ex;

import java.util.Scanner;

public class P10_TreasureHunt {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] initialLoot = scanner.nextLine().split("\\|");
        String command = scanner.nextLine();

        while (!command.equals("Yohoho!")) {
            String action = command.split("\\s+")[0];

            switch (action) {
                case "Loot":
                    String[] input = command.split("\\s+");
                    String[] items = getItems(input);
                    String[] validItems = getValidItems(initialLoot, items); //ако няма валидни елементи, стринг билдъра връща масив с елемент "" и следващия метод слага празен стринг отпред на резултата!
                    initialLoot = putItems(initialLoot, validItems);
                    break;
                case "Drop":
                    int index = Integer.parseInt(command.split("\\s+")[1]);
                    if (index >= 0 && index < initialLoot.length) {
                        dropItem(initialLoot, index);
                    }
                    break;
                case "Steal":
                    int count = Integer.parseInt(command.split("\\s+")[1]);

                    if (count < initialLoot.length) {
                        String[] stolenItems = new String[count];
                        for (int i = initialLoot.length - count; i < initialLoot.length; i++) {
                            stolenItems[i - (initialLoot.length - count)] = initialLoot[i];
                        }
                        initialLoot = remainingitems(initialLoot, count);
                        System.out.println(String.join(", ", stolenItems));
                    } else {
                        String[] stolenItems = new String[initialLoot.length];
                        for (int i = 0; i < initialLoot.length; i++) {
                            stolenItems[i] = initialLoot[i];
                        }
                        initialLoot = new String[0];
                        System.out.println(String.join(", ", stolenItems));
                    }
                    break;
            }


            command = scanner.nextLine();
        }
        double average = getAverageGain(initialLoot);

        if (initialLoot.length > 0) {
            System.out.printf("Average treasure gain: %.2f pirate credits.", average);
        } else {
            System.out.println("Failed treasure hunt.");
        }
    }

    private static double getAverageGain(String[] initialLoot) {
        int sum = 0;
        for (int i = 0; i < initialLoot.length; i++) {
            String currentItem = initialLoot[i];
            sum += currentItem.length();
        }
        double average = sum * 1.0 / initialLoot.length;
        return average;
    }

    private static String[] remainingitems(String[] initialLoot, int count) {
        String[] remainingItems = new String[initialLoot.length - count];
        for (int i = 0; i < remainingItems.length; i++) {
            remainingItems[i] = initialLoot[i];
        }
        return remainingItems;
    }

    private static void dropItem(String[] initialLoot, int index) {
        String item = initialLoot[index];
        for (int i = index; i < initialLoot.length - 1; i++) {
            initialLoot[i] = initialLoot[i + 1];
        }
        initialLoot[initialLoot.length - 1] = item;
    }

    private static String[] putItems(String[] initialLoot, String[] validItems) {
        if (validItems[0].equals("")) {
            return initialLoot;
        }
        if (validItems.length > 0 && initialLoot.length > 0) {
            String[] loot = new String[initialLoot.length + validItems.length];
            for (int i = validItems.length - 1; i >= 0; i--) {
                loot[validItems.length - 1 - i] = validItems[i];
            }
            for (int i = validItems.length; i < loot.length; i++) {
                loot[i] = initialLoot[i - validItems.length];
            }
            return loot;
        } else if (validItems.length == 0) {
            return initialLoot;

        } else {
            return validItems;
        }

    }

    private static String[] getValidItems(String[] initialLoot, String[] items) {
        StringBuilder validItems = new StringBuilder();

        for (int i = 0; i < items.length; i++) {
            boolean isExistItem = false;
            String currentItem = items[i];
            for (int j = 0; j < initialLoot.length; j++) {
                String existItem = initialLoot[j];
                if (currentItem.equals(existItem)) {
                    isExistItem = true;
                    break;
                }
            }
            if (!isExistItem) {
                validItems.append(currentItem);
                validItems.append(' ');
            }

        }
        String[] validItemsArr = String.valueOf(validItems).split("\\s+");
        return validItemsArr;
    }

    private static String[] getItems(String[] input) {
        String[] items = new String[input.length - 1];
        for (int i = 1; i < input.length; i++) {
            items[i - 1] = input[i];
        }
        return items;
    }
}
