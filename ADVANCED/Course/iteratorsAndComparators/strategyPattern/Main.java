package iteratorsAndComparators.comparingObjects.strategyPattern;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        NameComparator nameComparator = new NameComparator();
        AgeComparator ageComparator = new AgeComparator();

        Set<Person> personSetNameComp = new TreeSet<>(nameComparator);
        Set<Person> personSetNameAge = new TreeSet<>(ageComparator);

        for (int i = 0; i < n; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");
            String name = tokens[0];
            int age = Integer.parseInt(tokens[1]);
            Person person = new Person(name, age);
            personSetNameComp.add(person);
            personSetNameAge.add(person);
        }

        personSetNameComp.stream().sorted(nameComparator)
                .forEach(p -> System.out.println(p.getName() + " " + p.getAge()));


        personSetNameAge.stream().sorted(ageComparator)
                .forEach(p -> System.out.println(p.getName() + " " + p.getAge()));
    }
}

