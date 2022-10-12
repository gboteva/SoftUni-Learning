package StreamsAndDirectories;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class WordCount {
    public static void main(String[] args) {
        String words = "src/StreamsAndDirectories/exercise_resource/words.txt";
        String inputText = "src/StreamsAndDirectories/exercise_resource/text.txt";
        String output = "src/StreamsAndDirectories/exercise_resource/result.txt";

        Map<String, Integer> wordsCounter = new LinkedHashMap<>();
        try {
            Scanner wordScanner = new Scanner(new FileReader(words));
            PrintWriter writer = new PrintWriter(new FileWriter(output));

            while (wordScanner.hasNext()){
                String word = wordScanner.next();
               wordsCounter.put(word,0);
            }

            Scanner textScanner = new Scanner(new FileReader(inputText));
            while (textScanner.hasNext()){
                String word = textScanner.next();
                if (wordsCounter.containsKey(word)){
                    wordsCounter.put(word,wordsCounter.get(word) + 1);
                }
            }

            wordScanner.close();
            textScanner.close();

            wordsCounter.entrySet().forEach(e->{
                writer.println(e.getKey() + " - " + e.getValue());
            });

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
