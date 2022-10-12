package ex_p07_collectionHierarchy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] items = scanner.nextLine().split("\\s+");

        List<String> addCollectionList = new ArrayList<>();
        List<String> addRemoveCollectionList = new ArrayList<>();
        List<String> myListList = new ArrayList<>();

        AddCollection addCollection = new AddCollection();
        AddRemoveCollection addRemoveCollection = new AddRemoveCollection();
        MyListImpl myList = new MyListImpl();

        for (String item : items) {
            addCollectionList.add(String.valueOf(addCollection.add(item)));
            addRemoveCollectionList.add(String.valueOf(addRemoveCollection.add(item)));
            myListList.add(String.valueOf(myList.add(item)));
        }



        List<String> removedFromFirstColl = new ArrayList<>();
        List<String> removedFromSecondColl = new ArrayList<>();

        int countRemoveOperations = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i <countRemoveOperations ; i++) {
           removedFromFirstColl.add(addRemoveCollection.remove());
            removedFromSecondColl.add(myList.remove());
        }

        System.out.println(String.join(" ", addCollectionList));
        System.out.println(String.join(" ", addRemoveCollectionList));
        System.out.println(String.join(" ", myListList));
        System.out.println(String.join(" ", removedFromFirstColl));
        System.out.println(String.join(" ", removedFromSecondColl));


    }
}
