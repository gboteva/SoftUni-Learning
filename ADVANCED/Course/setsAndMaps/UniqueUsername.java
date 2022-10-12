package setsAndMaps;

import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class UniqueUsername {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        Set<String> usernames = new LinkedHashSet<>();

        while (n-- > 0){
            usernames.add(scanner.nextLine());
        }

        usernames.forEach(System.out::println);
    }
}
