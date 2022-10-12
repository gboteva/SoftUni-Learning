package StreamsAndDirectories;

import java.io.File;
import java.util.ArrayDeque;

public class GetFolderSize {
    public static void main(String[] args) {
        File folder = new File("src/StreamsAndDirectories/exercise_resource/Exercises Resources");

        ArrayDeque<File> directories = new ArrayDeque<>();
        directories.offer(folder);

        int sumOfBytes = 0;

        while (!directories.isEmpty()){
            File currentDirectory = directories.poll();
            File[] files = currentDirectory.listFiles();
            for (File file : files) {
                if (file.isDirectory()){
                    directories.offer(file);
                }else {
                    sumOfBytes+=file.length();
                }
            }
        }
        System.out.println("Folder size: " + sumOfBytes);
    }
}
