package setsAndMaps;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class PopulationCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Map<String, Map<String, Long>> statistic = new LinkedHashMap<>();

        Map<String, Long> countries = new LinkedHashMap<>();

        while (!input.equals("report")) {
            String[] tokens = input.split("\\|");
            String country = tokens[1];
            String city = tokens[0];
            long population = Long.parseLong(tokens[2]);

            if (!statistic.containsKey(country)) {
                statistic.put(country, new LinkedHashMap<>());
            }

            statistic.get(country).put(city, population);

            if (!countries.containsKey(country)){
                countries.put(country, population);
            }else {
                long currentPop = countries.get(country);
                countries.put(country, population+currentPop);
            }


            input = scanner.nextLine();
        }


        statistic.entrySet().stream()
                .sorted((f,s) -> countries.get(s.getKey()).compareTo(countries.get(f.getKey())))
                .forEach(country->{
                    System.out.printf("%s (total population: %d)%n", country.getKey(), countries.get(country.getKey()));
                    country.getValue().entrySet().stream()
                            .sorted((c1,c2)-> c2.getValue().compareTo(c1.getValue()))
                            .forEach(city-> System.out.printf("=>%s: %d%n", city.getKey(),city.getValue()));
                });

    }
}
