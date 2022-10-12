package StreamsFilesAndDirectories_04.ex;

import java.io.*;

public class P07_MergeTwoFiles {
    public static void main(String[] args) {
    String pathOne = "C:\\SoftUni\\JAVA_ADVANCED\\Учебни материали\\4_Streams_Files_Directories\\04. Java-Advanced-Streams-Files-and-Directories-Resources ex\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\inputOne.txt";
    String pathTwo = "C:\\SoftUni\\JAVA_ADVANCED\\Учебни материали\\4_Streams_Files_Directories\\04. Java-Advanced-Streams-Files-and-Directories-Resources ex\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\inputTwo.txt";
    String outPath = "C:\\SoftUni\\JAVA_ADVANCED\\Учебни материали\\4_Streams_Files_Directories\\04. Java-Advanced-Streams-Files-and-Directories-Resources ex\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\merged.txt";

        try(BufferedReader readerOne = new BufferedReader(new FileReader(pathOne))){
            BufferedWriter writer = new BufferedWriter(new FileWriter(outPath));
            String line = readerOne.readLine();
            while (line!=null){
                writer.write(line);
                writer.newLine();
                line = readerOne.readLine();
            }

            BufferedReader readerTwo = new BufferedReader(new FileReader(pathTwo));
            line = readerTwo.readLine();
            while (line!=null){
                writer.write(line);
                writer.newLine();
                line = readerTwo.readLine();
            }
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
