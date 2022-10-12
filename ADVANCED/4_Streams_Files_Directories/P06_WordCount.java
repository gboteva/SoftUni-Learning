package StreamsFilesAndDirectories_04.ex;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class P06_WordCount {
    public static void main(String[] args) {
        String firstPath = "C:\\SoftUni\\JAVA_ADVANCED\\Учебни материали\\4_Streams_Files_Directories\\04. Java-Advanced-Streams-Files-and-Directories-Resources ex\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\words.txt";
        String secondPath = "C:\\SoftUni\\JAVA_ADVANCED\\Учебни материали\\4_Streams_Files_Directories\\04. Java-Advanced-Streams-Files-and-Directories-Resources ex\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\text.txt";

        String outPath = "C:\\SoftUni\\JAVA_ADVANCED\\Учебни материали\\4_Streams_Files_Directories\\04. Java-Advanced-Streams-Files-and-Directories-Resources ex\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\result.txt";

        Map<String, Integer> words = new LinkedHashMap<>();


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outPath))){
            List<String> allWords = Files.readAllLines(Path.of(firstPath));
            for ( String lineWithWords:allWords ){
                Arrays.stream(lineWithWords.split("\\s+"))
                        .forEach(word->words.put(word, 0));
            }
            List<String> allLines = Files.readAllLines(Path.of(secondPath));
            for (String line : allLines ){
                Arrays.stream(line.split("\\s+")).forEach(word->{
                        if (words.containsKey(word)){
                            words.put(word, words.get(word)+1);
                        }
                });
            }
            words.entrySet().forEach(entry->{
                try {
                    writer.write(entry.getKey() + " - " + entry.getValue());
                    writer.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
