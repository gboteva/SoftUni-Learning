package setsAndMaps;

import java.util.*;
import java.util.stream.Collectors;

public class CountRealNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Double> numbers = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Double::parseDouble).collect(Collectors.toList());

        Map<Double, Integer> countMap = new LinkedHashMap<>();

        numbers.forEach(n-> {

            if (countMap.containsKey(n)){
                int currentCount = countMap.get(n);
                countMap.put(n, currentCount+1);
            } else {
                countMap.put(n, 1);
            }

        });

        countMap.entrySet().forEach(e-> System.out.printf("%.1f -> %d%n", e.getKey(), e.getValue()));
    }
}
