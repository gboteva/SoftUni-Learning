package beforeCourse.objectsAndClasses_6.ex.OpinionPoll_03;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int countLines = Integer.parseInt(scanner.nextLine());
        List<Person> peopleList = new ArrayList<>();

        for (int i = 1; i <=countLines ; i++) {
            String personalInfo = scanner.nextLine();
            String name = personalInfo.split(" ")[0];
            int age = Integer.parseInt(personalInfo.split(" ")[1]);
            Person currentPerson = new Person(name, age);

            if (age > 30) {
                peopleList.add(currentPerson);
            }
        }
       // peopleList.sort(Comparator.comparing(Person:: getAge));

        for ( Person person : peopleList ){
            System.out.println(person);
        }
    }
}
