package beforeCourse.AssociativeArrays_7.ex;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class P01_CountCharsInAString {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Map<Character, Integer> countSymbol = new LinkedHashMap<>();
        for ( char symbol:input.toCharArray() ){
            if (symbol==' '){
                continue;
            }
            if (countSymbol.containsKey(symbol)){
                countSymbol.put(symbol, countSymbol.get(symbol)+1);
            } else {
                countSymbol.put(symbol, 1);
            }
        }

        countSymbol.entrySet().forEach(entry-> System.out.printf("%c -> %d%n",entry.getKey(), entry.getValue()));

    }
}
