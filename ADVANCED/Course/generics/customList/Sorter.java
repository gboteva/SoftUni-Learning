package generics.customList;

public class Sorter {

    public static <T extends Comparable<T>> void sort(CustomList<T> list) {
        //list.sort();
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).compareTo(list.get(j))>0){
                    list.swap(i, j);
                }
            }
        }
    }
}
