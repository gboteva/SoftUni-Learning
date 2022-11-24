package implementations;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class ReversedListTest {
    @Test
    public void removeAtShouldRemoveCorrectElement() {
        ReversedList<Integer> list = new ReversedList<Integer>();
        list.add(0);
        list.add(1);
        list.add(2);
        list.removeAt(0);
        int element = list.get(0);
        Assert.assertEquals(2,element );
    }
}