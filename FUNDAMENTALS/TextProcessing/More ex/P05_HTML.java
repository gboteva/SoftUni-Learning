package FundamentalsCourse.textProcessing_8.moreEx;

import java.util.Scanner;



public class P05_HTML {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String articleName = scanner.nextLine();
        String articleContent = scanner.nextLine();

        String command = scanner.nextLine();
        System.out.println("<h1>");
        System.out.println("\t" + articleName);
        System.out.println("</h1>");
        System.out.println("<article>");
        System.out.println("\t" + articleContent);
        System.out.println("</article>");
        while (!command.equals("end of comments")){
            String comment = command;
            System.out.println("<div>");
            System.out.println("\t" + comment);
            System.out.println("</div>");
            command= scanner.nextLine();
        }

    }
}
