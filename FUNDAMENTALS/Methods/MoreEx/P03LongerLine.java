package before.course.methods_4.m.ex;

import java.util.Scanner;

public class P03LongerLine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x1 = Integer.parseInt(scanner.nextLine());
        int y1 = Integer.parseInt(scanner.nextLine());
        int x2 = Integer.parseInt(scanner.nextLine());
        int y2 = Integer.parseInt(scanner.nextLine());
        int x3 = Integer.parseInt(scanner.nextLine());
        int y3 = Integer.parseInt(scanner.nextLine());
        int x4 = Integer.parseInt(scanner.nextLine());
        int y4 = Integer.parseInt(scanner.nextLine());
        System.out.println(centerCoordinats(x1,y1,x2,y2,x3,y3,x4,y4));
    }
    private static String centerCoordinats(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        double first = Math.sqrt(Math.pow(x1,2) + Math.pow(y1,2));
        double second = Math.sqrt(Math.pow(x2,2) + Math.pow(y2,2));
        double third = Math.sqrt(Math.pow(x3,2) + Math.pow(y3,2));
        double fourd = Math.sqrt(Math.pow(x4,2) + Math.pow(y4,2));

        double firstLine = Math.sqrt(Math.pow((x2-x1),2) + Math.pow((y2-y1),2));
        double secondLine = Math.sqrt(Math.pow((x4-x3),2) + Math.pow((y4-y3),2));
        String result = "";

        if (firstLine>secondLine){      //първата линия е по-дълга
            if (second<first){
                result="("+x2+", "+y2+")("+x1+", "+y1+")";
            } else {
                result="("+x1+", "+y1+")("+x2+", "+y2+")";
            }

        } else if (secondLine>firstLine){
            if (fourd<third){
                result="("+x4+", "+y4+")("+x3+", "+y3+")";
            } else {
                result="("+x3+", "+y3+")("+x4+", "+y4+")";
            }
        } else if (secondLine==firstLine){
            if (second<first){
                result="("+x2+", "+y2+")("+x1+", "+y1+")";
            } else {
                result="("+x1+", "+y1+")("+x2+", "+y2+")";
            }
        }
        return result;
    }
}
