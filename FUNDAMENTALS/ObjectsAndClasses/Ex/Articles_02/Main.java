package beforeCourse.objectsAndClasses_6.ex.Articles_02;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] initialArticle = scanner.nextLine().split(", ");
        int countCommands = Integer.parseInt(scanner.nextLine());

        Article articleObject = new Article(initialArticle[0],initialArticle[1], initialArticle[2] );

        for (int i = 1; i <= countCommands; i++) {
            String commandLine = scanner.nextLine();
            String action = commandLine.split(": ")[0];
            String newValue = commandLine.split(": ")[1];
            switch (action) {
                case "Edit":
                    articleObject.setContent(newValue);
                    break;
                case "ChangeAuthor":
                    articleObject.setAuthor(newValue);
                    break;
                case "Rename":
                    articleObject.setTitle(newValue);
                    break;
            }
        }
        System.out.println(articleObject);
    }
}
