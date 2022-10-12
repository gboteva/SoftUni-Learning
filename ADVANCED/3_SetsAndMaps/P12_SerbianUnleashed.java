package SetsAndMaps_03.ex;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class P12_SerbianUnleashed {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Map<String, LinkedHashMap<String, Long>> map = new LinkedHashMap<>();
        String regex = "(?<name>\\w+\\s?\\w+\\s?\\w+)\\s{1}@(?<place>\\w+\\s?([A-z]+)?\\s?([A-z]+)?)\\s{1}(?<price>\\d+)\\s{1}(?<count>\\d+)";
        Pattern pattern = Pattern.compile(regex);

        while (!input.equals("End")) {
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                String singer = matcher.group("name");
                String place = matcher.group("place");
                int price =Integer.parseInt(matcher.group("price"));
                int countTickets = Integer.parseInt(matcher.group("count"));
                long amount = (long) price * countTickets;
                if (map.get(place) == null) {
                    map.put(place, new LinkedHashMap<>());
                    map.get(place).put(singer, amount);
                } else {
                    if (map.get(place).get(singer) == null) {
                        map.get(place).put(singer, amount);
                    } else {
                        long currentAmount = map.get(place).get(singer);
                        map.get(place).put(singer, currentAmount + amount);
                    }
                }

            }

            input = scanner.nextLine();
        }

        map.entrySet().stream().forEach(venue ->{
            System.out.println(venue.getKey());
            map.get(venue.getKey()).entrySet().stream().sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
                    .forEach(singer -> System.out.println("#  " + singer.getKey() + " -> " + singer.getValue()));
        });

    }
}
