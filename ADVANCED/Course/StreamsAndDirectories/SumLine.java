package StreamsAndDirectories;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SumLine {
    public static void main(String[] args) {
     String input = "src/StreamsAndDirectories/exercise_resource/input.txt";

     try {
         BufferedReader reader = new BufferedReader(new FileReader(input));
         String line = reader.readLine();

         while (line!=null){
             long sum = 0;
             for (char symbol : line.toCharArray()) {
                 sum+=symbol;
             }
             System.out.println(sum);
             line = reader.readLine();
         }

         reader.close();


     } catch (IOException e) {
         e.printStackTrace();
     }
    }
}
