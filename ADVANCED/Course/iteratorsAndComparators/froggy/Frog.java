package iteratorsAndComparators.comparingObjects.froggy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Frog implements Iterator<Integer> {
    private Lake lake;
    private int index;

    public Frog(Lake lake) {
        this.lake = lake;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        if (index<lake.getNumbers().size()){
            return true;
        }
        return false;
    }

    @Override
    public Integer next() {
        int num = lake.getNumbers().get(index);
        index = index + 2;
        return num;
    }

    public List<String> jump (){
        List<String> stones = new ArrayList<>();
        while (hasNext()){
            stones.add(String.valueOf(next()));
        }

        index = 1;
        while (hasNext()){
            stones.add(String.valueOf(next()));
        }
        return stones;
    }
}
