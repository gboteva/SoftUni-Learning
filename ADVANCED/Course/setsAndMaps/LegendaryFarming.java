package setsAndMaps;

import java.util.*;

public class LegendaryFarming {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Integer> keyMaterials = new TreeMap<>();
        keyMaterials.put("shards", 0);
        keyMaterials.put("fragments", 0);
        keyMaterials.put("motes", 0);

        Map<String, Integer> junks = new TreeMap<>();

        boolean isObtained = false;

        while (!isObtained) {
            String[] tokens = scanner.nextLine().split("\\s+");
            for (int i = 0; i < tokens.length; i += 2) {
                int quantity = Integer.parseInt(tokens[i]);
                String material = tokens[i + 1].toLowerCase();

                if (material.equals("shards") || material.equals("fragments") || material.equals("motes")) {
                    int currentQuantity = keyMaterials.get(material);
                    keyMaterials.put(material, currentQuantity + quantity);
                    if (keyMaterials.get(material) >= 250) {
                        isObtained = true;
                    }
                } else {
                    if (junks.containsKey(material)) {
                        int currentQuantity = junks.get(material);
                        junks.put(material, currentQuantity + quantity);
                    }else {
                        junks.put(material, quantity);
                    }


                }

                if (isObtained) {
                    break;
                }
            }

        }

        if (keyMaterials.get("shards") >= 250) {
            System.out.println("Shadowmourne obtained!");
            keyMaterials.put("shards", keyMaterials.get("shards") - 250);
        } else if (keyMaterials.get("fragments") >= 250) {
            System.out.println("Valanyr obtained!");
            keyMaterials.put("fragments", keyMaterials.get("fragments") - 250);
        } else if (keyMaterials.get("motes") >= 250) {
            System.out.println("Dragonwrath obtained!");
            keyMaterials.put("motes", keyMaterials.get("motes") - 250);
        }

        keyMaterials.entrySet().stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .forEach(e -> System.out.println(e.getKey() + ": " + e.getValue()));

        junks.entrySet().forEach(e -> System.out.println(e.getKey() + ": " + e.getValue()));

    }
}
