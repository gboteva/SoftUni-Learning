package Lab.p03_StudenSystem_refactoring;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentSystem studentSystem = new StudentSystem();

        String command = scanner.nextLine();

        while (!command.equals("Exit")) {
            String[] input = command.split("\\s+");
            if (input[0].equals("Create")){
                String name = input[1];
                int age = Integer.parseInt(input[2]);
                double grade = Double.parseDouble(input[3]);
                Student student = new Student(name, age, grade);
                studentSystem.create(student);
            }else if (input[0].equals("Show")){
                String searchedName = input[1];
               studentSystem.show(searchedName);
            }

            command = scanner.nextLine();
        }

    }
}