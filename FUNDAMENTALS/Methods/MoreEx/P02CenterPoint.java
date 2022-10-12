package before.course.methods_4.m.ex;

import java.util.Scanner;

public class P02CenterPoint {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x1 = Integer.parseInt(scanner.nextLine());
        int y1 = Integer.parseInt(scanner.nextLine());
        int x2 = Integer.parseInt(scanner.nextLine());
        int y2 = Integer.parseInt(scanner.nextLine());

        System.out.println(centerCoordinats(x1,y1,x2,y2));

    }

    private static String centerCoordinats(int x1, int y1, int x2, int y2) {
        String result = "(";

        double first = Math.sqrt(Math.pow(x1,2) + Math.pow(y1,2));
        double second = Math.sqrt(Math.pow(x2,2) + Math.pow(y2,2));
        if (first>second){
            result="("+x2+", "+y2+")";
        }else {
            result="("+x1+", "+y1+")";
        }
        return result;
    }
}
