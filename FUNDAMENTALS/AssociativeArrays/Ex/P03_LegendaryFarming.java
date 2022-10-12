package beforeCourse.AssociativeArrays_7.ex;

import java.util.*;

public class P03_LegendaryFarming {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Integer> validItems = new LinkedHashMap<>();
        validItems.put("shards", 0);
        validItems.put("fragments", 0);
        validItems.put("motes", 0);

        Map<String, Integer> junks =new LinkedHashMap<>();
        boolean isWin = false;
        while (!isWin) {
            String[] inputData = scanner.nextLine().toLowerCase().split(" ");
            for (int i = 0; i < inputData.length; i += 2) {
                int quantity = Integer.parseInt(inputData[i]);
                String item = inputData[i + 1];
                if (item.equals("shards") || item.equals("fragments") || item.equals("motes")) {
                    validItems.put(item, validItems.get(item) + quantity);

                    if (validItems.get(item) >= 250) {
                        validItems.put(item, validItems.get(item) - 250);
                        isWin = true;
                        switch (item) {
                            case "shards":
                                System.out.println("Shadowmourne obtained!");
                                break;
                            case "fragments":
                                System.out.println("Valanyr obtained!");
                                break;
                            case "motes":
                                System.out.println("Dragonwrath obtained!");
                                break;
                        }
                    }
                        if (isWin){
                            break;
                        }
                } else {
                    if (junks.containsKey(item)) {
                        junks.put(item, junks.get(item) + quantity);
                    } else {
                        junks.put(item, quantity);
                    }
                }
            }
        }

        validItems.entrySet().forEach(entry -> System.out.printf("%s: %d%n", entry.getKey(), entry.getValue()));
        junks.entrySet().forEach(entry -> System.out.printf("%s: %d%n", entry.getKey(), entry.getValue()));
    }
}
