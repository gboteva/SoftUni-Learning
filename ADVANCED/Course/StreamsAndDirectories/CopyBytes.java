package StreamsAndDirectories;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class CopyBytes {
    public static void main(String[] args) {
        String path = "C:\\SoftUni\\JAVA_ADVANCED\\Учебни материали\\04. Java-Advanced-Files-and-Streams-Lab-Resources\\input.txt";
        String outPath = "C:\\SoftUni\\JAVA_ADVANCED\\Учебни материали\\04. Java-Advanced-Files-and-Streams-Lab-Resources\\03.CopyBytesOutput.txt";

        try (FileInputStream inputStream = new FileInputStream(path)){
            FileOutputStream outputStream = new FileOutputStream(outPath);
            int oneByte = inputStream.read();

            while (oneByte>0){
                String digit = String.valueOf(oneByte);
                if (!digit.equals("32") && !digit.equals("10")){
                    for (int i = 0; i < digit.length(); i++) {
                        outputStream.write(digit.charAt(i));
                    }
                }else {
                    outputStream.write((char) oneByte);
                }


                oneByte = inputStream.read();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
