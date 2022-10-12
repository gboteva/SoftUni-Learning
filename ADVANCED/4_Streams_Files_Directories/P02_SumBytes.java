package StreamsFilesAndDirectories_04.ex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class P02_SumBytes {
    public static void main(String[] args) {
        String inPath = "C:\\SoftUni\\JAVA_ADVANCED\\Учебни материали\\4_Streams_Files_Directories\\04. Java-Advanced-Streams-Files-and-Directories-Resources ex\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\input.txt";

        try(BufferedReader br = new BufferedReader(new FileReader(inPath))){
            String line = br.readLine();
            long sum = 0;
            while (line!=null){
                for (char c : line.toCharArray() ){
                    sum+=c;
                }

                line = br.readLine();
            }
            System.out.println(sum);
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
