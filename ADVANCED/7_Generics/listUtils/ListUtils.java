package listUtils;
import java.util.Collections;
import java.util.List;

public class ListUtils<T> {

    public static <T extends Comparable<T>> T getMin(List<T> list) {
        if (!list.isEmpty()){
            return Collections.min(list);
        }else {
            throw new IllegalArgumentException("The list is empty!");
        }
    }

    public static <T extends Comparable<T>> T getMax(List<T> list){
        if (!list.isEmpty()){
            return Collections.max(list);
        }else {
            throw new IllegalArgumentException("The list is empty!");
        }
    }
}
