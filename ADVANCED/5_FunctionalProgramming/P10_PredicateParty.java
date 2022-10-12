package FunctionalPrograming.ex;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class P10_PredicateParty {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> people = Arrays.stream(scanner.nextLine().split("\\s+")).collect(Collectors.toList());

        String command = scanner.nextLine();
        while (!command.equals("Party!")) {
            String[] commandParts = command.split("\\s+");
            String action = commandParts[0];
            String typeCommand = commandParts[1];
            String part = commandParts[2];

            switch (action) {
                case "Remove":
                    people.removeIf(getPredicate(typeCommand, part));

                    break;
                case "Double":
                    for (int i = 0; i < people.size(); i++) {
                        String guest = people.get(i);
                        if (getPredicate(typeCommand, part).test(guest)) {
                            people.add(i++, guest);
                        }
                    }
                    break;
            }

            command = scanner.nextLine();
        }

        if (!people.isEmpty()) {
            Collections.sort(people);
            System.out.print(String.join(", ", people));
            System.out.print(" are going to the party!");
        } else {
            System.out.println("Nobody is going to the party!");
        }

    }

    private static Predicate<String> getPredicate(String type, String parameter) {
        switch (type) {
            case "StartsWith":
                return text -> text.startsWith(parameter);
            case "EndsWith":
                return text -> text.endsWith(parameter);
            case "Length":
                return text -> text.length() == Integer.parseInt(parameter);
            default:
                return text -> false;
        }
    }
}
