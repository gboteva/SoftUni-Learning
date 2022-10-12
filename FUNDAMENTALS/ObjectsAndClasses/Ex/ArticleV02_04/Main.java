package beforeCourse.objectsAndClasses_6.ex.ArticleV02_04;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int countCommands = Integer.parseInt(scanner.nextLine());
        List<Article> articlesList = new ArrayList<>();
        for (int i = 1; i <=countCommands ; i++) {
            String inputLine = scanner.nextLine();
            String title = inputLine.split(", ")[0];
            String content = inputLine.split(", ")[1];
            String author = inputLine.split(", ")[2];

            Article currentArticle = new Article(title,content,author);
            articlesList.add(currentArticle);
        }
        String input = scanner.nextLine();

        for (Article items : articlesList ){
            System.out.println(items);
        };

    }
}
