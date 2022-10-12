package beforeCourse.objectsAndClasses_6.ex.OrderByAge_07;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        List<Person> peopleList = new ArrayList<>();
        while (!input.equals("End")){
            String name = input.split(" ")[0];
            String id = input.split(" ")[1];
            int age = Integer.parseInt(input.split(" ")[2]);
            Person currentPerson = new Person(name,id,age);
            peopleList.add(currentPerson);

            input= scanner.nextLine();
        }
        peopleList.sort(Comparator.comparing(Person::getAge));
        for ( Person person: peopleList ){
            System.out.println(person);
        }
    }
}
