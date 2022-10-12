package StreamsAndDirectories;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CreateZiArchive {
    public static void main(String[] args) {
        try {
            ZipOutputStream zos = new ZipOutputStream(new FileOutputStream("src/StreamsAndDirectories/exercise_resource/Exercises Resources/files.zip"));

            FileInputStream fis = new FileInputStream("src/StreamsAndDirectories/exercise_resource/Exercises Resources/words.txt");

            int byteContainer;

            zos.putNextEntry(new ZipEntry("words.txt"));

            while ((byteContainer = fis.read()) != -1) {
                zos.write(byteContainer);
            }
            zos.closeEntry();
            zos.putNextEntry(new ZipEntry("text.txt"));

            fis = new FileInputStream("src/StreamsAndDirectories/exercise_resource/Exercises Resources/text.txt");

            while ((byteContainer = fis.read()) != -1) {
                zos.write(byteContainer);
            }
            zos.closeEntry();
            zos.putNextEntry(new ZipEntry("inputLineNumbers.txt"));
            fis = new FileInputStream("src/StreamsAndDirectories/exercise_resource/Exercises Resources/inputLineNumbers.txt");

            while ((byteContainer = fis.read()) != -1) {
                zos.write(byteContainer);
            }
            zos.closeEntry();
            zos.finish();
            zos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
