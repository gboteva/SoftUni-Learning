package StreamsFilesAndDirectories_04.ex;

import java.io.File;

public class P08_GetFolderSize {
    public static void main(String[] args) {
        String path = "C:\\SoftUni\\JAVA_ADVANCED\\Учебни материали\\4_Streams_Files_Directories\\04. Java-Advanced-Streams-Files-and-Directories-Resources ex\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\Exercises Resources";

        File folder = new File(path);
        File[] allFiles = folder.listFiles();

        int size = 0;
        for (File file : allFiles ){
            size+=file.length();
        }
        System.out.println("Folder size: " + size);
    }
}
