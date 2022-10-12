package StreamsFilesAndDirectories_04.ex;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class P04_CountCharacterTypes {
    public static void main(String[] args) {
        String inPath = "C:\\SoftUni\\JAVA_ADVANCED\\Учебни материали\\4_Streams_Files_Directories\\04. Java-Advanced-Streams-Files-and-Directories-Resources ex\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\input.txt";
        String outPath = "C:\\SoftUni\\JAVA_ADVANCED\\Учебни материали\\4_Streams_Files_Directories\\04. Java-Advanced-Streams-Files-and-Directories-Resources ex\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\output.txt";
        List<Character> vowels = new ArrayList<>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');
        List<Character> punctuation = new ArrayList<>();
        punctuation.add('!');
        punctuation.add(',');
        punctuation.add('.');
        punctuation.add('?');

        try (BufferedReader br = new BufferedReader(new FileReader(inPath))){
            BufferedWriter writer = new BufferedWriter(new FileWriter(outPath));
            String line = br.readLine();
            int countVowels = 0;
            int countSigns = 0;
            int countConsonants= 0;
            while (line!=null){

                for (char c: line.toCharArray() ){
                    if (vowels.contains(c)){
                        countVowels++;
                    }else if (punctuation.contains(c)){
                        countSigns++;
                    }else if (c!=32) {
                        countConsonants++;
                    }
                }
                line = br.readLine();
            }
            writer.write("Vowels: " + countVowels);
            writer.newLine();
            writer.write("Consonants: " + countConsonants);
            writer.newLine();
            writer.write("Punctuation: " + countSigns);
            writer.newLine();
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
