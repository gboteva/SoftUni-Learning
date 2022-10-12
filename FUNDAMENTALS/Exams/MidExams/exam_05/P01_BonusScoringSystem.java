package FundamentalsCourse.midExamMid.exam_05;

import java.util.Scanner;

public class P01_BonusScoringSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfStudents = Integer.parseInt(scanner.nextLine());
        int allLectures = Integer.parseInt(scanner.nextLine());
        int additionalBonus = Integer.parseInt(scanner.nextLine());

        double maxBonus = Double.MIN_VALUE;
        int attendanceOfStudentWithMaxBonus = 0;



        for (int i = 1; i <= numberOfStudents; i++) {
            int countAttendances = Integer.parseInt(scanner.nextLine());
            double totalBonus = ((countAttendances * 1.0 / allLectures)) * (5 + additionalBonus);
            if (totalBonus > maxBonus) {
                maxBonus = totalBonus;
                attendanceOfStudentWithMaxBonus = countAttendances;
            }
        }
        if (numberOfStudents==0 || allLectures==0){
            System.out.println("Max Bonus: 0.");
            System.out.println("The student has attended 0 lectures.");
        }else {
            System.out.printf("Max Bonus: %.0f.%n", Math.ceil(maxBonus));
            System.out.printf("The student has attended %d lectures.", attendanceOfStudentWithMaxBonus);
        }
    }
}
