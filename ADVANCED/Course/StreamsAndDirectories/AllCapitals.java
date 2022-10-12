package StreamsAndDirectories;

import java.io.*;

public class AllCapitals {
    public static void main(String[] args) {
        String input = "src/StreamsAndDirectories/exercise_resource/input.txt";
        String output = "src/StreamsAndDirectories/exercise_resource/output.txt";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(input));
            PrintWriter writer = new PrintWriter(new FileWriter(output));
            String line = reader.readLine();

            while (line!=null){
                line = line.toUpperCase();
                writer.println(line);
                line = reader.readLine();
            }

            reader.close();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
