package iteratorsAndComparators.comparingObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        List<Person> people = new ArrayList<>();

        while (!input.equals("END")){
            String[] tokens = input.split("\\s+");
            Person person = new Person(tokens[0], Integer.parseInt(tokens[1]), tokens[2]);
            people.add(person);

            input = scanner.nextLine();
        }
        int index = Integer.parseInt(scanner.nextLine())-1;

        Person person = people.get(index);
        int countEquals = 0;
        for (Person p : people) {
            if (p.compareTo(person) == 0){
                countEquals++;
            }
        }

      if (countEquals-1 == 0){
          System.out.println("No matches");
      }else {
          System.out.printf("%d %d %d", countEquals, people.size()-countEquals, people.size());
      }
    }
}
