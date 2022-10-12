package StreamsFilesAndDirectories_04.ex;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class P09_CopyPicture {
    public static void main(String[] args) throws IOException {

        FileInputStream input = new FileInputStream("C:\\SoftUni\\JAVA_ADVANCED\\Учебни материали\\4_Streams_Files_Directories\\kak-se-pravi-kuchechka-kolibka.jpg");
        FileOutputStream output = new FileOutputStream("C:\\SoftUni\\JAVA_ADVANCED\\Учебни материали\\4_Streams_Files_Directories\\my-copied-picture.jpg");

        int byteCounter = 0;
        while ((byteCounter = input.read())!=-1){

            output.write(byteCounter);
        }
        input.close();
        output.close();

    }
}
