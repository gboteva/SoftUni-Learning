package StreamsAndDirectories;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class ReadFile {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String path = "C:\\SoftUni\\JAVA_ADVANCED\\Учебни материали\\04. Java-Advanced-Files-and-Streams-Lab-Resources\\input.txt";

        try (FileInputStream inputStream = new FileInputStream(path)) {
            int oneByte = inputStream.read();

            while (oneByte>0){
                System.out.printf("%s ", Integer.toBinaryString(oneByte));
                oneByte = inputStream.read();
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
