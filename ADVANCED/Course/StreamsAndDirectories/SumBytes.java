package StreamsAndDirectories;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SumBytes {
    public static void main(String[] args) {
        String input = "src/StreamsAndDirectories/exercise_resource/input.txt";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(input));
            String line = reader.readLine();
            long sum = 0;
            while (line != null) {

                for (char symbol : line.toCharArray()) {
                    sum += symbol;
                }

                line = reader.readLine();
            }
            System.out.println(sum);
            reader.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
