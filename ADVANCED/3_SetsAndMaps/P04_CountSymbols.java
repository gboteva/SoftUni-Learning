package SetsAndMaps_03.ex;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class P04_CountSymbols {
    public static <Char> void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();

        Map<String, Integer> occurrences = new TreeMap<>();

        for (int i = 0; i < text.length(); i++) {
            String currentSymbol = String.valueOf(text.charAt(i));
            if (!occurrences.containsKey(currentSymbol)){
                occurrences.put(currentSymbol,1);
            }else {
                int count = occurrences.get(currentSymbol);
                occurrences.put(currentSymbol,count+1 );
            }


        }

        occurrences.entrySet().forEach(e->{
            String symbol = e.getKey();
            int count = e.getValue();
            System.out.printf("%s: %d time/s%n", symbol,count);
        });

    }
}
