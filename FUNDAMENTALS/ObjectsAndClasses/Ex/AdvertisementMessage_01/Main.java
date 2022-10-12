package beforeCourse.objectsAndClasses_6.ex.AdvertisementMessage_01;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = Integer.parseInt(scanner.nextLine());
        List <String> phrases = Arrays.asList("Such a great product.", "I always use that product.", "Best product of its category.", "Exceptional product.", "I can’t live without this product.");
        List<String> events = Arrays.asList("Now I feel good.", "I have succeeded with this product.", "Makes miracles. I am happy of the results!", "I cannot believe but now I feel awesome.", "Try it yourself, I am very satisfied.", "I feel great!");
        List<String> authors = Arrays.asList("Diana", "Petya", "Stella", "Elena", "Katya", "Iva", "Annie", "Eva");
        List<String> cities = Arrays.asList("Burgas", "Sofia", "Plovdiv", "Varna", "Ruse");

        for (int i = 1; i <=count ; i++) {
            Random words = new Random();
            int x = words.nextInt(phrases.size());
            int y = words.nextInt(phrases.size());
            int z = words.nextInt(phrases.size());
            int w = words.nextInt(phrases.size());
            Message randomMessage = new Message(phrases.get(x), events.get(y),authors.get(z), cities.get(w));

            System.out.println(randomMessage);
        }

    }
}
