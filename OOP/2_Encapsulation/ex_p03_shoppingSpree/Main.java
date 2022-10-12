package ex_p03_shoppingSpree;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] firstInputLine = scanner.nextLine().split(";");
        String[] secondInputLine = scanner.nextLine().split(";");
        Map<String, Person> people = new LinkedHashMap<>();
        Map<String, Product> products = new HashMap<>();

        for (int i = 0; i < firstInputLine.length; i++) {
            String[] personInfo = firstInputLine[i].split("=");
            String name = personInfo[0];
            double money = Double.parseDouble(personInfo[1]);
            Person person = new Person(name, money);
            people.put(name, person);
        }
        for (int i = 0; i < secondInputLine.length; i++) {
            String[] productInfo = secondInputLine[i].split("=");
            String name = productInfo[0];
            double cost = Double.parseDouble(productInfo[1]);
            Product product = new Product(name, cost);
            products.put(name, product);
        }

        String personAndProduct = scanner.nextLine();

        while (!personAndProduct.equals("END")){
            String personName = personAndProduct.split("\\s+")[0];
            String productName = personAndProduct.split("\\s+")[1];

            Person currentPerson = people.get(personName);
            Product currentProduct = products.get(productName);
            try {
                currentPerson.buyProduct(currentProduct);
                System.out.printf("%s bought %s%n", currentPerson.getName(), currentProduct.getName());
            } catch (Exception e){
                System.out.printf("%s can't afford %s%n", currentPerson.getName(), currentProduct.getName());
            }


            personAndProduct = scanner.nextLine();
        }

        people.entrySet().forEach(e->{
            System.out.print(e.getKey() + " - ");
            List<Product> productList = e.getValue().getProducts();
            List<String> productsName = new ArrayList<>();
            productList.stream().forEach(product -> {
               productsName.add(product.getName());
            });
            if (!productsName.isEmpty()){
                System.out.println(String.join(", ", productsName));
            }else {
                System.out.println("Nothing bought");
            }

            });

    }
}
