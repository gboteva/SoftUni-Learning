package StreamsAndDirectories;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyPictures {
    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream("src/StreamsAndDirectories/exercise_resource/Exercises Resources/picture.jpg");
            FileOutputStream fos = new FileOutputStream("src/StreamsAndDirectories/exercise_resource/Exercises Resources/picture-copy.jpg");

            byte[] buffer = new byte[1024];
            while(fis.read(buffer) >= 0){
                fos.write(buffer);
            }

            fis.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
