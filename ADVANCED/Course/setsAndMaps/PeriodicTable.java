package setsAndMaps;

import java.util.*;

public class PeriodicTable {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int lines = Integer.parseInt(scanner.nextLine());

        Set<String> chemicalElement = new TreeSet<>();

        while (lines -- > 0){
            String [] element = scanner.nextLine().split("\\s+");
            Arrays.stream(element).forEach(e->chemicalElement.add(e));
        }

        Iterator<String> iterator = chemicalElement.iterator();

        while (iterator.hasNext()){
          System.out.print(iterator.next() + " ");
       }
    }
}
