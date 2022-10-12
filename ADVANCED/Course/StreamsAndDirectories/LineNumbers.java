package StreamsAndDirectories;

import java.io.*;
import java.util.Scanner;

public class LineNumbers {
    public static void main(String[] args) {
        String input = "src/StreamsAndDirectories/exercise_resource/inputLineNumbers.txt";
        String output = "src/StreamsAndDirectories/exercise_resource/output.txt";

        try {
            Scanner scanner = new Scanner(new FileReader(input));
            PrintWriter writer = new PrintWriter(new FileWriter(output));

            int counter = 1;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                writer.println(counter + ". " + line);
                counter++;

            }
            scanner.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
