package StreamsAndDirectories;

import java.io.File;
import java.nio.file.Files;

public class ListFiles {
    public static void main(String[] args) {
        File file = new File("C:\\SoftUni\\JAVA_ADVANCED\\Учебни материали\\04. Java-Advanced-Files-and-Streams-Lab-Resources\\Files-and-Streams");
//        boolean isExisting = file.exists();
//        long length = file.length();
//        boolean isDirectory = file.isDirectory();
//        File[] files = file.listFiles();

        if (file.exists()) {
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (File f : files) {
                    if (!f.isDirectory()) {
                        System.out.printf("%s: [%s]%n",
                                f.getName(), f.length());
                    }
                }
            }
        }

    }
}
