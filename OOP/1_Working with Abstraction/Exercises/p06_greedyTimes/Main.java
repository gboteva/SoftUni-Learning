
package greedyTimes;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        long bagCapacity = Long.parseLong(scanner.nextLine());
        String[] itemsInSafe = scanner.nextLine().split("\\s+");

        Bag bag = new Bag(bagCapacity);

        for (int i = 0; i < itemsInSafe.length; i += 2) {
            String itemName = itemsInSafe[i];
            long quantity = Long.parseLong(itemsInSafe[i + 1]);

            Item item = Item.createItem(itemName, quantity);

            if (item == null) {
                continue;
            }

            bag.putInTheBag(item);

        }


        bag.print();
    }
}