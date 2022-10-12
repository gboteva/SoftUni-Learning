package StreamsAndDirectories;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

public class SortLines {
    public static void main(String[] args) {
        Path path = Path.of("C:\\SoftUni\\JAVA_ADVANCED\\Учебни материали\\04. Java-Advanced-Files-and-Streams-Lab-Resources\\input.txt");
        Path outPath = Path.of("C:\\SoftUni\\JAVA_ADVANCED\\Учебни материали\\04. Java-Advanced-Files-and-Streams-Lab-Resources\\06.SortLinesOutput.txt");

        try {
            List<String> lines = Files.readAllLines(path);
            Collections.sort(lines);
            Files.write(outPath, lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
