package FundamentalsCourse.finalExam.exam02;

import com.sun.security.jgss.GSSUtil;

import java.util.*;

public class P03_PlantDiscovery {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        Map<String, Integer> info = new LinkedHashMap<>();      //plants and rarity
        for (int i = 0; i < n; i++) {
            String[] input = scanner.nextLine().split("<->");
            String plant = input[0];
            int rarity = Integer.parseInt(input[1]);
            info.put(plant, rarity);
        }
        String command = scanner.nextLine();

        Map<String,List<Integer>> ratingMap = new LinkedHashMap<>();
        info.entrySet().forEach(e->ratingMap.put(e.getKey(),new ArrayList<>()));

        while (!command.equals("Exhibition")){
            String action = command.split(": ")[0];
            String plant = command.split(": ")[1].split(" - ")[0];

            switch (action){
                case "Rate": //add rating
                    int rating = Integer.parseInt(command.split(": ")[1].split(" - ")[1]);
                    if (ratingMap.get(plant)!=null){
                        ratingMap.get(plant).add(rating);
                    }else {
                        System.out.println("error");
                    }
                    break;
                case "Update": //update rarity
                    int newRarity = Integer.parseInt(command.split(": ")[1].split(" - ")[1]);
                    if (info.get(plant)!=null){
                        info.put(plant,newRarity);
                    }else {
                        System.out.println("error");
                    }
                    break;
                case "Reset":
                    if (ratingMap.get(plant)!=null){
                        ratingMap.get(plant).clear();
                    }else {
                        System.out.println("error");
                    }
                    break;
            }

            command= scanner.nextLine();
        }
        //map with plants and rarity - info
        // map with plants and List of ratings - ratingMap;

        Map<String, List<Double>> averageMap = new LinkedHashMap<>();
        //map with plants and List of average Rating and Rarity  - averageMap

        for (Map.Entry<String, List<Integer>> entry: ratingMap.entrySet() ){
            List <Integer> rating = entry.getValue();
            double average = rating.stream().mapToDouble(x->x).average().orElse(0); //calculate average rating of each plant
            averageMap.put(entry.getKey(), new ArrayList<>());      //add plants in Average Map
            averageMap.get(entry.getKey()).add(average);        //add avg Rating in Average Map
        }


        for (Map.Entry<String, Integer> entry: info.entrySet() ){
            averageMap.get(entry.getKey()).add(Double.valueOf(entry.getValue())); //add rarity in Average Map
        }

        System.out.println("Plants for the exhibition:");
        for ( Map.Entry<String, List<Double>> entry: averageMap.entrySet()){
            System.out.printf("- %s; Rarity: %.0f; Rating: %.2f%n", entry.getKey(), entry.getValue().get(1), entry.getValue().get(0));
        }


    }
}
