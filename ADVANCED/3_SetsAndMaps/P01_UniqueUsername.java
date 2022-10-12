package SetsAndMaps_03.ex;

import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class P01_UniqueUsername {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        Set<String> words = new LinkedHashSet<>();
        for (int i = 0; i <n ; i++) {
            String text = scanner.nextLine();
            words.add(text);
        }
        words.forEach(System.out::println);
    }
}

