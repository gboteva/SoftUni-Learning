package StreamsAndDirectories;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SerializeArrayList {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(7);
        list.add(9);
        list.add(55);

        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("src/StreamsAndDirectories/list.ser"));
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("src/StreamsAndDirectories/list.ser"));

            outputStream.writeObject(list); //serialize -> write to file

            List<Integer> listFromFile = (ArrayList<Integer>) inputStream.readObject();  //deserialize, -> read from file

            System.out.println(listFromFile.get(0));
            System.out.println(listFromFile.get(3));

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
