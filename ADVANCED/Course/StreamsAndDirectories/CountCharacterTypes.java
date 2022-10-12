package StreamsAndDirectories;

import java.io.*;
import java.util.Scanner;

public class CountCharacterTypes {
    public static void main(String[] args) {
        String input = "src/StreamsAndDirectories/exercise_resource/input.txt";
        String output = "src/StreamsAndDirectories/exercise_resource/output.txt";

        try {
            Scanner scanner = new Scanner(new FileReader(input));
            PrintWriter writer = new PrintWriter(new FileWriter(output));

            int countVowel = 0;
            int countConsonants = 0;
            int countPunctuation = 0;



            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                for (char symbol : line.toCharArray()) {
                    if (symbol == '.' || symbol == ',' || symbol == '?' || symbol == '!') {
                        countPunctuation++;
                    } else if (symbol == 'a' || symbol == 'e' || symbol == 'i' || symbol == 'o' || symbol == 'u') {
                        countVowel++;
                    } else if (symbol != ' ') {
                        countConsonants++;
                    }
                }
            }

            writer.println("Vowels: " + countVowel);
            writer.println("Consonants: " + countConsonants);
            writer.println("Punctuation: " + countPunctuation);

            scanner.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
