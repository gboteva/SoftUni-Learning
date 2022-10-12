package StreamsFilesAndDirectories_04.ex;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class P05_LineNumbers {
    public static void main(String[] args) {
        String inPath = "C:\\SoftUni\\JAVA_ADVANCED\\Учебни материали\\4_Streams_Files_Directories\\04. Java-Advanced-Streams-Files-and-Directories-Resources ex\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\inputLineNumbers.txt";
        String outPath = "C:\\SoftUni\\JAVA_ADVANCED\\Учебни материали\\4_Streams_Files_Directories\\04. Java-Advanced-Streams-Files-and-Directories-Resources ex\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\output.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outPath))){
            List<String> lines = Files.readAllLines(Path.of(inPath));
            int counter = 1;
            for (String line: lines){
                writer.write(counter + ". " + line);
                writer.newLine();
                counter++;
            }

        }catch (IOException e){
            e.printStackTrace();
        }



    }
}
