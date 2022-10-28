package greedyTimes;

import java.util.*;

public class Bag {
    private long capacity;

    private Map<ItemTypes, Map<String, Item>> bag;

    public Bag(long capacity) {
        this.capacity = capacity;
        bag = new LinkedHashMap<>();
    }


    public void putInTheBag(Item item) {

        if (isEnoughSpace(item) && ensureRules(item)) {
            ItemTypes currentType = item.getItemTypes();

            if (!bag.containsKey(currentType)) {
                bag.put(currentType, new TreeMap<>());

                if (!bag.get(currentType).containsKey(item.getName())) {
                    bag.get(currentType).put(item.getName(), item);

                } else {
                    Item itemFromBag = bag.get(item.getItemTypes()).get(item.getName());
                    itemFromBag.increaseQuantity(item.getQuantity());
                    bag.get(currentType).put(item.getName(), itemFromBag);
                }

            } else {

                if (!bag.get(currentType).containsKey(item.getName())) {
                    bag.get(currentType).put(item.getName(), item);

                } else {
                    Item itemFromBag = bag.get(item.getItemTypes()).get(item.getName());
                    itemFromBag.increaseQuantity(item.getQuantity());
                    bag.get(currentType).put(item.getName(), itemFromBag);
                }

            }

        }

    }

    private boolean isEnoughSpace(Item item) {
        return this.capacity >= bag.values().stream()
                .map(Map::values)
                .flatMap(Collection::stream)
                .mapToLong(Item::getQuantity).sum() + item.getQuantity();
    }

    private boolean ensureRules(Item item) {
        //gold >= gem >= cash

        switch (item.getItemTypes()) {
            case Gold:

                long futureQuantityGold = 0;
                if (bag.containsKey(ItemTypes.Gold)) {
                    futureQuantityGold = bag.get(ItemTypes.Gold).values().stream().mapToLong(Item::getQuantity).sum() + item.getQuantity();
                } else {
                    futureQuantityGold = item.getQuantity();
                }

                if (bag.containsKey(ItemTypes.Gem) && bag.containsKey(ItemTypes.Cash)) {
                    long currentQuantityGems = bag.get(ItemTypes.Gem).values().stream().mapToLong(Item::getQuantity).sum();
                    long currentQuantityCash = bag.get(ItemTypes.Cash).values().stream().mapToLong(Item::getQuantity).sum();

                    if (futureQuantityGold >= currentQuantityGems && futureQuantityGold > currentQuantityCash) {
                        return true;
                    }
                } else if (bag.containsKey(ItemTypes.Gem)) {
                    long currentQuantityGems = bag.get(ItemTypes.Gem).values().stream().mapToLong(Item::getQuantity).sum();
                    if (futureQuantityGold >= currentQuantityGems) {
                        return true;
                    }
                } else if (bag.containsKey(ItemTypes.Cash)) {
                    long currentQuantityCash = bag.get(ItemTypes.Cash).values().stream().mapToLong(Item::getQuantity).sum();
                    if (futureQuantityGold >= currentQuantityCash) {
                        return true;
                    }
                } else if (!bag.containsKey(ItemTypes.Gem) && !bag.containsKey(ItemTypes.Cash)) {
                    return true;
                }

                return false;


            case Gem:
                long futureQuantityGems = 0;

                if (bag.containsKey(ItemTypes.Gem)) {
                    futureQuantityGems = bag.get(ItemTypes.Gem).values().stream().mapToLong(Item::getQuantity).sum() + item.getQuantity();
                } else {
                    futureQuantityGems = item.getQuantity();
                }

                if (!bag.containsKey(ItemTypes.Gold)) {
                    return false;

                } else if (bag.containsKey(ItemTypes.Gold) && bag.containsKey(ItemTypes.Cash)) {
                    long currentQuantityGold = bag.get(ItemTypes.Gold).values().stream().mapToLong(Item::getQuantity).sum();
                    long currentQuantityCash = bag.get(ItemTypes.Cash).values().stream().mapToLong(Item::getQuantity).sum();

                    if (currentQuantityGold >= futureQuantityGems && futureQuantityGems >= currentQuantityCash) {
                        return true;
                    }
                }  else if (bag.containsKey(ItemTypes.Gold) && !bag.containsKey(ItemTypes.Cash))  {
                    long currentQuantityGold = bag.get(ItemTypes.Gold).values().stream().mapToLong(Item::getQuantity).sum();
                    if (currentQuantityGold >= futureQuantityGems){
                        return true;
                    }
                }

                return false;

            case Cash:
                long futureQuantityCash = 0;
                if (bag.containsKey(ItemTypes.Cash)) {
                    futureQuantityCash = bag.get(ItemTypes.Cash).values().stream().mapToLong(Item::getQuantity).sum() + item.getQuantity();
                } else {
                    futureQuantityCash = item.getQuantity();
                }

                if (!bag.containsKey(ItemTypes.Gem)) {
                    return false;

                } else {
                    long currentQuantityGems = bag.get(ItemTypes.Gem).values().stream().mapToLong(Item::getQuantity).sum();

                    if (futureQuantityCash <= currentQuantityGems) {
                        return true;
                    }
                }
                return false;

        }
        return false;
    }


    public void print() {
        for (var type : bag.entrySet()) {

            long sumValues = type.getValue().values()
                    .stream()
                    .mapToLong(Item::getQuantity).sum();

            System.out.println(String.format("<%s> $%s", type.getKey(), sumValues));


            type.getValue().entrySet().stream()
                    .sorted((e1, e2) -> e2.getKey().compareTo(e1.getKey()))
                    .forEach(i -> System.out.println("##" + i.getKey() + " - " + i.getValue().getQuantity()));

        }
    }
}
