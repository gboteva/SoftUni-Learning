package FunctionalPrograming.ex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class P11_ThePartyReservationFilterModule {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> people = Arrays.stream(scanner.nextLine().split("\\s+")).collect(Collectors.toList());
        String command = scanner.nextLine();
        List<String> removed = new ArrayList<>();
        while (!command.equals("Print")) {
            String action = command.split(";")[0];
            String type = command.split(";")[1];
            String argument = command.split(";")[2];

            if (action.contains("Add")) {
                removed.add(removedPeople(people, type, argument));
                people.removeIf(getPredicate(type, argument)); //ok

            } else if (action.contains("Remove")) {
                people.add(removePredicate(removed,type,argument));
            }

            command = scanner.nextLine();
        }

        System.out.println(String.join(" ", people));
    }

    public static String removedPeople(List<String> people, String type, String argument) {
       String removedPeople = "";
        switch (type) {
            case "Starts with":
                for (String name : people) {
                    if (name.startsWith(argument)) {
                        return name;
                    }
                }
                break;

            case "Ends with":
                for (String name : people) {
                    if (name.endsWith(argument)) {
                       return name;
                    }
                }
                break;

            case "Length":
                for (String name : people) {
                    if (name.length() == Integer.parseInt(argument)) {
                       return name;
                    }
                }
                break;

            case "Contains":
                for (String name : people) {
                    if (name.contains(argument)) {
                       return name;
                    }
                }
                break;

        }
        return removedPeople;
    }

    public static Predicate<String> getPredicate(String type, String argument) {
        switch (type) {
            case "Starts with":
                return (e -> e.startsWith(argument));

            case "Ends with":
                return e -> e.endsWith(argument);

            case "Length":
                return e -> e.length() == Integer.parseInt(argument);

            case "Contains":
                return e -> e.contains(argument);

        }
        return e -> false;
    }

    public static String removePredicate(List<String> removed, String type, String argument) {
        String names= "";
        switch (type) {
            case "Starts with":
                for (String name : removed) {
                    if (name.startsWith(argument)) {
                        return name;
                    }
                }
                break;
            case "Ends with":
                for (String name : removed) {
                    if (name.endsWith(argument)) {
                        return name;
                    }
                }
                break;

            case "Length":
                for (String name : removed) {
                    if (name.length() == Integer.parseInt(argument)) {
                        return name;
                    }
                }
                break;

            case "Contains":
                for (String name : removed) {
                    if (name.contains(argument)) {
                        return name;
                    }
                }
                break;

        }
        return names;
    }
}
