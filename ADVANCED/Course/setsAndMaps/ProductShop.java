package setsAndMaps;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class ProductShop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        Map<String, Map<String, Double>> shops = new TreeMap<>();

        while (!input.equals("Revision")){
            String [] tokens = input.split(", ");
            String shopName = tokens[0];
            String product = tokens[1];
            double price = Double.parseDouble(tokens[2]);

            shops.putIfAbsent(shopName, new LinkedHashMap<>());

            shops.get(shopName).put(product, price);

            input = scanner.nextLine();
        }

        for (Map.Entry<String, Map<String, Double>> entry : shops.entrySet()) {
            String shopName = entry.getKey();
            Map<String, Double> productsInfo = entry.getValue();

            System.out.println(shopName + "->");
            for (Map.Entry<String, Double> product : productsInfo.entrySet()) {
                System.out.printf("Product: %s, Price: %.1f%n", product.getKey(), product.getValue());
            }

        }

    }
}
