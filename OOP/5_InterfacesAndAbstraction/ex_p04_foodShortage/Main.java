package ex_p04_foodShortage;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       Scanner scanner = new Scanner (System.in);
        int n = Integer.parseInt(scanner.nextLine());
        Map<String, Buyer> buyerMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");
            String name = tokens[0];
            int age = Integer.parseInt(tokens[1]);

            if (tokens.length==4){
                String id = tokens[2];
                String birthDate = tokens[3];
                Citizen citizen = new Citizen(name, age, id, birthDate);
                buyerMap.put(name, citizen);
            }else {
                String group = tokens[2];
                Rebel rebel = new Rebel(name,age, group);
                buyerMap.put(name, rebel);
            }
        }

        String line = scanner.nextLine();

        while (!line.equals("End")){
            String searchedName = line;

            if (buyerMap.containsKey(searchedName)){
                buyerMap.get(searchedName).buyFood();
            }

            line = scanner.nextLine();
        }

        System.out.println(buyerMap.entrySet().stream().mapToInt(e -> e.getValue().getFood()).sum());
    }
}
