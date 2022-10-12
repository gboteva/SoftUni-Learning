package StreamsAndDirectories;

import java.io.*;

public class SerializeCustomObject {
    public static void main(String[] args)  {
        Course course = new Course();
        course.name = "Java Advanced September 2022";
        course.numbenrOfStudents = 250;

        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("src/StreamsAndDirectories/courses.ser"));
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("src/StreamsAndDirectories/courses.ser"));

            outputStream.writeObject(course); //serialize -> write to file

            Course courseFromFile = (Course) inputStream.readObject(); //deserialize, -> read from file
            System.out.println(courseFromFile.numbenrOfStudents);
            System.out.println(courseFromFile.name);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
