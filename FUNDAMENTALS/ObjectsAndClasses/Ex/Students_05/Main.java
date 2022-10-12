package beforeCourse.objectsAndClasses_6.ex.Students_05;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int countStudents = Integer.parseInt(scanner.nextLine());
        List<Student> studentsList = new ArrayList<>();
        for (int i = 1; i <=countStudents ; i++) {
            String [] studentInfo = scanner.nextLine().split(" ");

            Student currentStudent = new Student(studentInfo[0], studentInfo[1],Double.parseDouble(studentInfo[2]));
            studentsList.add(currentStudent);
        }

        studentsList.sort(Comparator.comparing(Student::getGrade).reversed());
        for (Student student : studentsList ){
            System.out.println(student);
        }
    }
}
