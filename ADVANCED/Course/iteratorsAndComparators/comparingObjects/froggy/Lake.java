package iteratorsAndComparators.comparingObjects.froggy;

import java.util.Iterator;
import java.util.List;


public class Lake implements Iterable<Integer> {
    private List<Integer> numbers;

    public Lake(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Frog(this);
    }

}
