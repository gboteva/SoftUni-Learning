package StreamsAndDirectories;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class MergeTwoFiles {
    public static void main(String[] args) {
        Path firstFile = Paths.get("src/StreamsAndDirectories/exercise_resource/inputOne.txt");
        Path secondFile = Paths.get("src/StreamsAndDirectories/exercise_resource/inputTwo.txt");
        Path output = Path.of("src/StreamsAndDirectories/exercise_resource/output.txt");

        try {
            List<String> first = Files.readAllLines(firstFile);
            List<String> second = Files.readAllLines(secondFile);
            Files.write(output, first, StandardOpenOption.APPEND);
            Files.write(output, second, StandardOpenOption.APPEND);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
