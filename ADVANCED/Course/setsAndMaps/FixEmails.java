package setsAndMaps;

import java.util.*;

public class FixEmails {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Map<String, List<String>> personAndEmails = new LinkedHashMap<>();

        while (!input.equals("stop")){
            String name = input;
            String email = scanner.nextLine();

            personAndEmails.putIfAbsent(name, new ArrayList<>());
            personAndEmails.get(name).add(email);

            input = scanner.nextLine();
        }

        personAndEmails.entrySet().forEach(person -> {
            String name = person.getKey();
            List<String> mails = person.getValue();

            mails.forEach(m-> {
                if (!m.toLowerCase().endsWith("us") && !m.toLowerCase().endsWith("uk")
                &&  !m.toLowerCase().endsWith("com")){
                    System.out.println(name + " -> " + m);
                }
            });

        });

    }
}
