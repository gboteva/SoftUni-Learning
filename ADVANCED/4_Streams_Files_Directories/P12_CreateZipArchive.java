package StreamsFilesAndDirectories_04.ex;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class P12_CreateZipArchive {
    public static void main(String[] args) throws IOException {
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream("C:\\SoftUni\\JAVA_ADVANCED\\Учебни материали\\4_Streams_Files_Directories\\files.zip"));

        FileInputStream fis = new FileInputStream(new File("C:\\SoftUni\\JAVA_ADVANCED\\Учебни материали\\4_Streams_Files_Directories\\04. Java-Advanced-Streams-Files-and-Directories-Resources ex\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\words.txt"));

        int byteContainer;

        zos.putNextEntry(new ZipEntry("words.txt"));

        while((byteContainer = fis.read())!=-1){
            zos.write(byteContainer);
        }
        zos.closeEntry();
        zos.putNextEntry(new ZipEntry("text.txt"));

        fis = new FileInputStream(
                new File("C:\\SoftUni\\JAVA_ADVANCED\\Учебни материали\\4_Streams_Files_Directories\\04. Java-Advanced-Streams-Files-and-Directories-Resources ex\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\text.txt"));
        while((byteContainer = fis.read())!=-1){
            zos.write(byteContainer);
        }
        zos.closeEntry();
        zos.putNextEntry(new ZipEntry("inputLineNumbers.txt"));
        fis = new FileInputStream(
                new File("C:\\SoftUni\\JAVA_ADVANCED\\Учебни материали\\4_Streams_Files_Directories\\04. Java-Advanced-Streams-Files-and-Directories-Resources ex\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\inputLineNumbers.txt"));
        while((byteContainer = fis.read())!=-1){
            zos.write(byteContainer);
        }
        zos.closeEntry();
        zos.finish();
        zos.close();
    }
}
