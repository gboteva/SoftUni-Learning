package FundamentalsCourse.midExamMid.exam_02;

import java.util.Scanner;

public class P01_SoftUniReception {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int countStudentsFirstEmplPerHour = Integer.parseInt(scanner.nextLine());
        int countStudentsSecondEmplPerHour = Integer.parseInt(scanner.nextLine());
        int countStudentsThirdEmplPerHour = Integer.parseInt(scanner.nextLine());
        int allStudents = Integer.parseInt(scanner.nextLine());

        int sumOfAllAnsweredStudents = 0;
        int allStudentsPerHour = countStudentsFirstEmplPerHour+countStudentsSecondEmplPerHour+countStudentsThirdEmplPerHour;
        int hours = 0;
        while (sumOfAllAnsweredStudents<allStudents){
            sumOfAllAnsweredStudents+=allStudentsPerHour;
            hours++;
            if (hours%4==0){
                hours++;
            }
        }
        System.out.printf("Time needed: %dh.", hours);
    }
}
