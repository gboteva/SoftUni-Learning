package beforeCourse.AssociativeArrays_7.ex;

import java.util.*;

public class P06_Courses {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Map<String, List<String>> courses = new LinkedHashMap<>();

        while (!input.equals("end")) {
            String nameOfCourse = input.split(" : ")[0];
            String student = input.split(" : ")[1];

            if (!courses.containsKey(nameOfCourse)) {
                courses.put(nameOfCourse, new ArrayList<>());
                courses.get(nameOfCourse).add(student);
            } else {
                courses.get(nameOfCourse).add(student);
            }

            input = scanner.nextLine();
        }

        courses.entrySet().stream()
                .forEach(e -> {
                    System.out.println(String.format("%s: %d", e.getKey(), e.getValue().size()));
                    e.getValue().stream().forEach(p -> System.out.println(String.format("-- %s",p)));
                });
    }
}