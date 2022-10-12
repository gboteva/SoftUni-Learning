package stacksAndQueues;

import java.util.ArrayDeque;
import java.util.Scanner;

public class PrinterQueue {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String fileName = scanner.nextLine();

        ArrayDeque<String> printerHistory = new ArrayDeque<>();

        while (!fileName.equals("print")){
            if (fileName.equals("cancel")){
                if (printerHistory.isEmpty()){
                    System.out.println("Printer is on standby");
                }else {
                    System.out.println("Canceled " + printerHistory.pop());
                }

            }else {
                printerHistory.offer(fileName);
            }

            fileName = scanner.nextLine();
        }

        printerHistory.stream().forEach(System.out::println);
    }
}
