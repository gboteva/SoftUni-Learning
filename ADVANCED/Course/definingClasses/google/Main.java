package definingClasses.google;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Person> people = new LinkedHashMap<>();

        String line = scanner.nextLine();
        while (!line.equals("End")) {
            String[] data = line.split("\\s+");
            String personName = data[0];

            if (!people.containsKey(personName)) {
                Person person = new Person(personName);
                people.put(personName, person);
            }

            Person currentPerson = people.get(personName);

            currentPerson.addObject(data);

            line = scanner.nextLine();
        }

        String searchedName = scanner.nextLine();

        System.out.println(people.get(searchedName));
    }
}
