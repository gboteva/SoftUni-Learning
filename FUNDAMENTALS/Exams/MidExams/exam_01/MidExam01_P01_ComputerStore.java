package FundamentalsCourse.midExamMid.exam_01;

import java.util.Scanner;

public class MidExam01_P01_ComputerStore {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        double sum = 0;
        while (!command.equals("special") && !command.equals("regular")){
            double currentPrice = Double.parseDouble(command);
            if (currentPrice<0){
                System.out.println("Invalid price!");
                command= scanner.nextLine();
                continue;
            }
            sum+=currentPrice;
            command= scanner.nextLine();
        }
        double taxes = sum*0.2;
        double totalPrice = sum+taxes;
        if (command.equals("special")){
            totalPrice*=0.9;
        }
        if (totalPrice==0){
            System.out.println("Invalid order!");
        }else {
            System.out.println("Congratulations you've just bought a new computer!");
            System.out.printf("Price without taxes: %.2f$%n", sum);
            System.out.printf("Taxes: %.2f$%n", taxes);
            System.out.println("-----------");
            System.out.printf("Total price: %.2f$", totalPrice);
        }
    }
}
