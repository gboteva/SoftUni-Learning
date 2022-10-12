package StreamsAndDirectories;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WriteToFile {
    public static void main(String[] args) {
        String path = "C:\\SoftUni\\JAVA_ADVANCED\\Учебни материали\\04. Java-Advanced-Files-and-Streams-Lab-Resources\\input.txt";
        String outPath = "C:\\SoftUni\\JAVA_ADVANCED\\Учебни материали\\04. Java-Advanced-Files-and-Streams-Lab-Resources\\02.WriteToFileOutput.txt";

        List<Character> punctuation = new ArrayList<>();
        punctuation.add('.');
        punctuation.add(',');
        punctuation.add('?');
        punctuation.add('!');

        try (FileInputStream inputStream = new FileInputStream(path)) {
            FileOutputStream outputStream = new FileOutputStream(outPath);

            int oneByte = inputStream.read();

            while (oneByte>0){
                char symbol = (char) oneByte;
                if (!punctuation.contains(symbol)){
                    outputStream.write(oneByte);
                }

                oneByte = inputStream.read();
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
