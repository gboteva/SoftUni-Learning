package StreamsFilesAndDirectories_04.ex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class P01_SumLines {
    public static void main(String[] args) {
        String inPath = "C:\\SoftUni\\JAVA_ADVANCED\\Учебни материали\\4_Streams_Files_Directories\\04. Java-Advanced-Streams-Files-and-Directories-Resources ex\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\input.txt";

       try(BufferedReader bf = new BufferedReader(new FileReader(inPath))){
           String line = bf.readLine();
           while (line!=null){
               long sum = 0;
               for (char c :line.toCharArray() ){
                   sum+=c;
               }
               System.out.println(sum);
               line = bf.readLine();
           }

       }catch (IOException e){
           e.printStackTrace();
       }
    }
}
