package FundamentalsCourse.finalExam.exam05;

import java.util.*;

public class P03_PIrates {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        Map<String, List<Integer>> target = new LinkedHashMap<>();

        while (!command.equals("Sail")){
            String [] array = command.split("\\|\\|");
            String city = array[0];
            int population = Integer.parseInt(array[1]);
            int gold = Integer.parseInt(array[2]);

            if (target.get(city)==null){
                target.put(city, new ArrayList<>());
                target.get(city).add(0,population);
                target.get(city).add(1,gold);
            }else {
                int currentPopulation = target.get(city).get(0);
                int currentGold = target.get(city).get(1);
                target.get(city).set(0, currentPopulation+population);
                target.get(city).set(1, currentGold+gold);
            }

            command= scanner.nextLine();
        }

        String nextCommand = scanner.nextLine();

        while (!nextCommand.equals("End")){
            String [] events = nextCommand.split("=>");
            String action = events[0];
            String town = events[1];
//key = city; value: 0-population; 1-gold
            switch (action){
                case "Plunder":
                    int people = Integer.parseInt(events[2]);
                    int gold = Integer.parseInt(events[3]);
                    int currentPeople = target.get(town).get(0);
                    int currentGold = target.get(town).get(1);
                    target.get(town).set(0, currentPeople-people);
                    target.get(town).set(1, currentGold-gold);
                    System.out.printf("%s plundered! %d gold stolen, %d citizens killed.%n", town, gold, people);

                    if (target.get(town).get(0)<=0){
                        target.remove(town);
                        System.out.printf("%s has been wiped off the map!%n", town);
                    }else if (target.get(town).get(1)<=0){
                        target.remove(town);
                        System.out.printf("%s has been wiped off the map!%n", town);
                    }

                    break;
                case "Prosper":
                    int prosperGold = Integer.parseInt(events[2]);
                    if (prosperGold<0){
                        System.out.println("Gold added cannot be a negative number!");
                    }else {
                        int existGold = target.get(town).get(1);
                        target.get(town).set(1, existGold+prosperGold);
                        System.out.printf("%d gold added to the city treasury. %s now has %d gold.%n", prosperGold, town,target.get(town).get(1));
                    }
                    break;
            }

            nextCommand= scanner.nextLine();
        }
        if (!target.isEmpty()){
            System.out.printf("Ahoy, Captain! There are %d wealthy settlements to go to:%n", target.size());
            for (Map.Entry<String, List<Integer>> city : target.entrySet()) {
                System.out.printf("%s -> Population: %d citizens, Gold: %d kg%n", city.getKey(), city.getValue().get(0), city.getValue().get(1));
            }
        }else {
            System.out.println("Ahoy, Captain! All targets have been plundered and destroyed!");
        }
    }
}
