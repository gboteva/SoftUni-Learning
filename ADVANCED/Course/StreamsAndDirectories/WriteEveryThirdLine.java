package StreamsAndDirectories;

import java.io.*;
import java.nio.file.Path;

public class WriteEveryThirdLine {
    public static void main(String[] args) {
        String path = "C:\\SoftUni\\JAVA_ADVANCED\\Учебни материали\\04. Java-Advanced-Files-and-Streams-Lab-Resources\\input.txt";
        String outPath = "C:\\SoftUni\\JAVA_ADVANCED\\Учебни материали\\04. Java-Advanced-Files-and-Streams-Lab-Resources\\05.WriteEveryThirdLineOutput.txt";


        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            PrintWriter writer = new PrintWriter(new FileWriter(outPath));

            int counter = 1;
            String line = reader.readLine();
            while (line!=null){
                if (counter%3==0){
                    writer.println(line);
                }
                counter++;
                line = reader.readLine();
            }

            reader.close();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
