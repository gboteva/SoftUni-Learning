package StreamsFilesAndDirectories_04.ex;

import java.io.*;

public class P11_SerializeCustomObject {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\SoftUni\\JAVA_ADVANCED\\Учебни материали\\4_Streams_Files_Directories\\course.ser"));
        Course course = new Course("advanced", 15);
        oos.writeObject(course);
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\SoftUni\\JAVA_ADVANCED\\Учебни материали\\4_Streams_Files_Directories\\course.ser"));

        Course loadCourse =(Course) ois.readObject();
        ois.close();

    }
}
