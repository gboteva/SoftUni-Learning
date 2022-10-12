package beforeCourse.AssociativeArrays_7.ex;

import java.util.*;

public class P04_Orders {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        Map<String, List<Double>> orders = new LinkedHashMap<>();


        while (!input.equals("buy")){
            String product = input.split(" ")[0];
            double price =Double.parseDouble(input.split(" ")[1]);
            double quantity =Double.parseDouble(input.split(" ")[2]);

            if (!orders.containsKey(product)){
                orders.put(product, new ArrayList<>());
                orders.get(product).add(price);
                orders.get(product).add(quantity);
            }else {
                orders.get(product).set(0,price);
                double newQuantity = orders.get(product).get(1)+quantity;
                orders.get(product).set(1,newQuantity);
            }

            input = scanner.nextLine();
        }
        orders.entrySet()
                .stream()
                .forEach(o -> System.out.println(String.format("%s -> %.2f",
                        o.getKey(), o.getValue().get(0) * o.getValue().get(1))));

    }
}
