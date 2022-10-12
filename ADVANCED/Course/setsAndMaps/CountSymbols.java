package setsAndMaps;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class CountSymbols {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        Map<Character, Integer> occurrences = new TreeMap<>();

        for (char symbol : text.toCharArray()) {
            if (!occurrences.containsKey(symbol)){
                occurrences.put(symbol, 1);
            }else {
              int currentOccurrences = occurrences.get(symbol);
              occurrences.put(symbol, currentOccurrences+1);
            }
        }

        occurrences.entrySet().forEach(e->{
            System.out.println(e.getKey() +": " + e.getValue() + " time/s");
        });

    }
}
