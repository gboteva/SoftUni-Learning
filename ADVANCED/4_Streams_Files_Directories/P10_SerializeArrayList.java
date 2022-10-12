package StreamsFilesAndDirectories_04.ex;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class P10_SerializeArrayList {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectOutput oos = new ObjectOutputStream(new FileOutputStream("C:\\SoftUni\\JAVA_ADVANCED\\Учебни материали\\4_Streams_Files_Directories\\double.list"));
        List<Double> doubles = new ArrayList<>();
        doubles.add(5.6);
        doubles.add(45.6);
        doubles.add(3.46);
        oos.writeObject(doubles);
        oos.flush();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\SoftUni\\JAVA_ADVANCED\\Учебни материали\\4_Streams_Files_Directories\\double.list"));
        System.out.println(ois.readObject());
        ois.close();

    }
}
