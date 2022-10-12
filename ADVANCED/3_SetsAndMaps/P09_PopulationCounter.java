package SetsAndMaps_03.ex;

import java.util.*;

public class P09_PopulationCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Map<String, LinkedHashMap<String, Long>> statistic = new LinkedHashMap<>();
        Map<String, Long> countries = new LinkedHashMap<>();

        while (!input.equals("report")){
            String [] data = input.split("\\|");
            String country = data[1];
            String city = data[0];
            Long population = Long.parseLong(data[2]);

            if (statistic.get(country)==null){
                statistic.put(country, new LinkedHashMap<>(){{
                    put(city, population);
                }});
            }else {
                statistic.get(country).put(city, population);
            }

            if (countries.get(country)==null){
                countries.put(country, population);
            }else {
                countries.put(country, countries.get(country) + population);
            }
            input = scanner.nextLine();
        }

        statistic.entrySet().stream()
                .sorted((f,s)->countries.get(s.getKey()).compareTo(countries.get(f.getKey())))
                        .forEach(country->{
                            System.out.printf("%s (total population: %d)%n", country.getKey(), countries.get(country.getKey()));
                            country.getValue().entrySet().stream()
                                    .sorted((c1,c2)-> c2.getValue().compareTo(c1.getValue()))
                                    .forEach(city-> System.out.printf("=>%s: %d%n", city.getKey(),city.getValue()));
                        });

    }
}
