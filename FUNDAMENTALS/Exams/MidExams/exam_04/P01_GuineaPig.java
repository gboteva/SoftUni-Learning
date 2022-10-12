package FundamentalsCourse.midExamMid.exam_04;

import java.util.Scanner;

public class P01_GuineaPig {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double foodKg = Double.parseDouble(scanner.nextLine());
        double hayKg = Double.parseDouble(scanner.nextLine());
        double coverKg = Double.parseDouble(scanner.nextLine());
        double weightKg = Double.parseDouble(scanner.nextLine());

        double excessFood =  foodKg*1000;
        double excessHay = hayKg*1000;
        double excessCover = coverKg*1000;
        int countDay = 0;

        while (excessFood>0 && excessHay>0 && excessCover>0 && countDay<30){
            countDay++;
            excessFood-= 300;

            if (countDay%2==0){
                excessHay-=excessFood*0.05;
            }
            if (countDay%3==0){
                excessCover-=(weightKg*1000)/3;
            }

        }

        if (countDay==30 && excessFood>0 && excessHay>0 && excessCover>0){
            System.out.printf("Everything is fine! Puppy is happy! Food: %.2f, Hay: %.2f, Cover: %.2f.", excessFood/1000, excessHay/1000, excessCover/1000);
        } else {
            System.out.printf("Merry must go to the pet store!");
        }
    }
}
