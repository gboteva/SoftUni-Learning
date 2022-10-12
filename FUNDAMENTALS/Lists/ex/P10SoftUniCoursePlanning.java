package beforeCourse.lists_5.ex;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class P10SoftUniCoursePlanning {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> input = Arrays.stream(scanner.nextLine().split(", ")).collect(Collectors.toList());
        String command = scanner.nextLine();

        while (!command.equals("course start")) {
            String action = command.split(":")[0];
            String lessonsTitle = command.split(":")[1];

            switch (action) {
                case "Add":
                    if (!input.contains(lessonsTitle)) {
                        input.add(lessonsTitle);
                    }
                    break;
                case "Insert":
                    int indexToInsert = Integer.parseInt(command.split(":")[2]);
                    if (!input.contains(lessonsTitle)) {
                        if (indexToInsert >= 0 && indexToInsert < input.size()) {
                            input.add(indexToInsert, lessonsTitle);
                        }
                    }
                    break;
                case "Remove":
                    input.remove(lessonsTitle);
                    input.remove(lessonsTitle + "-Exercise");
                    break;

                case "Swap":
                    String swapWithLesson = command.split(":")[2];
                    String firstLessonEx = lessonsTitle + "-Exercise";
                    String secondLessonEx = swapWithLesson + "-Exercise";
                    if (input.contains(lessonsTitle) && input.contains(swapWithLesson)) {
                        int lessonTitleIndex = input.indexOf(lessonsTitle);
                        int swapWithIndex = input.indexOf(swapWithLesson);
                        //Collections.swap(input,lessonTitleIndex, swapWithIndex);
                        input.add(swapWithIndex, lessonsTitle);
                        input.remove(swapWithLesson);
                        input.remove(lessonsTitle);
                        input.add(lessonTitleIndex, swapWithLesson);
                    }
                    if (input.contains(firstLessonEx)) {
                        input.remove(firstLessonEx);
                        input.add(input.indexOf(lessonsTitle) + 1, firstLessonEx);
                    }
                    if (input.contains(secondLessonEx)) {
                        input.remove(secondLessonEx);
                        input.add(input.indexOf(swapWithLesson) + 1, secondLessonEx);
                    }
                    break;
                case "Exercise":
                    String exercise = lessonsTitle + "-Exercise";
                    if (input.contains(lessonsTitle) && !input.contains(exercise)) {
                        input.add(input.indexOf(lessonsTitle) + 1, exercise);
                    } else if (!input.contains(lessonsTitle)) {
                        input.add(lessonsTitle);
                        input.add(exercise);
                    }
                    break;
            }
            command = scanner.nextLine();
        }

        for ( int i = 0; i < input.size(); i++) {
            System.out.printf("%d.%s%n", i + 1, input.get(i));
        }
    }
}

