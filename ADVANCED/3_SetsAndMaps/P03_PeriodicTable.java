package SetsAndMaps_03.ex;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class P03_PeriodicTable {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        Set<String> uniqueElements = new TreeSet<>();

        for (int i = 0; i < n; i++) {
            String [] element = scanner.nextLine().split("\\s+");
            uniqueElements.addAll(Arrays.asList(element));
        }
        uniqueElements.forEach(el-> System.out.print(el + " "));
    }
}
