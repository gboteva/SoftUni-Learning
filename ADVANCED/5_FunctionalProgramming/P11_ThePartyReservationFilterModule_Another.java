package FunctionalPrograming.ex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class P11_ThePartyReservationFilterModule_Another {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if(input.length()==0){
            return;
        }
        List<String> names = Arrays.stream(input.split("\\s+")).collect(Collectors.toList());
        String line = scanner.nextLine();
        List<String> commandLines =  new ArrayList<>();

        while (!line.equals("Print")) {
            String[] lineParts = line.split(";");
            String commands = lineParts[0];
            String type = lineParts[1];
            String toCheck = lineParts[2];

            if (commands.equals("Add filter")) {
                commandLines.add(line);
            } else if (commands.equals("Remove filter")) {
                String commandToRemove = "Add filter;" + type + ";" + toCheck;
                commandLines.removeIf(s -> s.equals(commandToRemove));
            }
            line = scanner.nextLine();
        }
        for (String command : commandLines) {
            String[] lineParts = command.split(";");
            String type = lineParts[1];
            String toCheck = lineParts[2];

            Predicate<String> startsWith = name -> name.startsWith(toCheck);
            Predicate<String> endsWith = name -> name.endsWith(toCheck);
            Predicate<String> validLenght = name -> name.length() == Integer.parseInt(toCheck);
            Predicate<String> containsLetter = name -> name.contains(toCheck);

            switch (type) {
                case "Starts with":
                    names.removeIf(startsWith);
                    break;
                case "Ends with":
                    names.removeIf(endsWith);
                    break;
                case "Length":
                    names.removeIf(validLenght);
                    break;
                case "Contains":
                    names.removeIf(containsLetter);
                    break;
            }
        }
        System.out.println(names.stream().collect(Collectors.joining(" ")).toString().replaceAll("([\\[\\]])", ""));
    }
}
