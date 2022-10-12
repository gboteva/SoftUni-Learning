package StreamsAndDirectories;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class ExtractIntegers {
    public static void main(String[] args) {
        String path = "C:\\SoftUni\\JAVA_ADVANCED\\Учебни материали\\04. Java-Advanced-Files-and-Streams-Lab-Resources\\input.txt";
        String outPath = "C:\\SoftUni\\JAVA_ADVANCED\\Учебни материали\\04. Java-Advanced-Files-and-Streams-Lab-Resources\\04.ExtractIntegersOutput.txt";

        try {
            Scanner scanner = new Scanner(new FileInputStream(path));
            PrintWriter writer = new PrintWriter(outPath);

            while (scanner.hasNext()){
                if (scanner.hasNextInt()){
                    writer.println(scanner.nextInt());
                }
                scanner.next();

            }
            scanner.close();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
