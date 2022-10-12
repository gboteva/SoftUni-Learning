package setsAndMaps;

import java.util.*;

public class CitiesByContinentAndCountry {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        Map<String, Map<String, List<String>>> cities = new LinkedHashMap<>();

        while (n-- > 0){
          String[] tokens = scanner.nextLine().split("\\s+");
            String continent = tokens[0];
            String country = tokens[1];
            String city = tokens[2];

            cities.putIfAbsent(continent, new LinkedHashMap<>());
            cities.get(continent).putIfAbsent(country, new ArrayList<>());
            cities.get(continent).get(country).add(city);

        }

        for (Map.Entry<String, Map<String, List<String>>> entry : cities.entrySet()) {
            String continent = entry.getKey();
            System.out.println(continent + ":");
            Map<String, List<String>> countries = entry.getValue();
            for (Map.Entry<String, List<String>> country : countries.entrySet()) {
                String countryName = country.getKey();
                String city = String.join(", ", country.getValue());
                System.out.println("  " + countryName + " -> " + city);
            }
        }

    }
}
