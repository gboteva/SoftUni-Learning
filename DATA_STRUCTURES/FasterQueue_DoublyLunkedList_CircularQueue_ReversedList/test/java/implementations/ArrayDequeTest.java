package implementations;

import org.junit.Assert;
import org.junit.Test;

public class ArrayDequeTest{
    ArrayDeque<String> deque= new ArrayDeque<>();

    @Test
    public void testGrow(){
        fill();
        deque.add("3");
        Assert.assertEquals(14, this.deque.capacity());
        Assert.assertEquals("3", deque.get(3));
    }

    @Test
    public void testAdd_ShouldAddElementInTheEndOFDeque(){
        fill();
        deque.add("Gosho");
        Assert.assertEquals("Gosho",deque.get(deque.size()-1));
        Assert.assertEquals("0",deque.get(0));
    }

    @Test
    public void testAddFirs_ShouldAddElementInFrontOfDeque(){
        fill();
        deque.addFirst("Gosho");
        Assert.assertEquals("Gosho", deque.get(0));
        Assert.assertEquals(4, deque.size());
    }

    @Test
    public void testSet_ShouldSetNewElementAtTheGivenIndex(){
        fill();
        deque.set(deque.size()-1, "Pesho");
        Assert.assertEquals("Pesho", deque.get(2));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testSet_ShouldThrowIfTryToSetAtInvalidIndex(){
        fill();
        deque.set(3,"Gosho");
    }

    @Test
    public void testPeek_ShouldReturnNullIsDequeIsEmpty(){
        Assert.assertNull(deque.peek());
    }

    @Test
    public void testPeek_ShouldReturnLastInElement(){
        fill();
        deque.push("Pesho");
        Assert.assertEquals("Pesho", deque.peek());
    }

    @Test
    public void testPeek_ShouldReturnFirstInElement(){
        fill();
        deque.offer("Pesho");
        Assert.assertEquals("0", deque.peek());
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testInsertShouldThrowIfTryToInsertAtInvalidIndex(){
        fill();
        deque.insert(3, "Pesho");
    }

    @Test
    public void testInsert_ShouldInsertElementAtTheGivenValidIndex(){
        fill();
        deque.insert(0, "Gosho");
        deque.insert(3, "Pesho");
        Assert.assertEquals("Gosho", deque.get(0));
        Assert.assertEquals("0", deque.get(1));
        Assert.assertEquals("Pesho", deque.get(3));
        Assert.assertEquals("2", deque.get(4));
    }

    @Test
    public void testGetByObject_ShouldReturnValidObject(){
        fill();
        Assert.assertEquals("2", deque.get("2"));
    }

    @Test
    public void testGetByObject_ShouldReturnNullIfThereIsNotSearchedElement(){
        fill();
        Assert.assertNull( deque.get("5"));
    }

    @Test
    public void testRemoveAtIndex_ShouldRemoveElementAtTheValidIndex(){
        fill();
        String removed = deque.remove(2);
        Assert.assertEquals(2, deque.size());
        Assert.assertEquals("1", deque.get(1));
        Assert.assertEquals(removed, "2");
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testRemoveAtIndex_ShouldRemoveThrowWhenTryToRemoveElementAtInvalidIndex(){
        fill();
        deque.remove(3);
    }

    @Test
    public void testRemoveLast_ShouldRemoveValidElement(){
        fill();
        deque.offer("Gosho");
        String element = deque.removeLast();
        Assert.assertEquals("Gosho", element);
        Assert.assertEquals(3, deque.size());
    }

    @Test
    public void testPoll_ShouldRemoveValidElement(){
        deque.offer("Gosho");
        deque.offer("Pesho");
        deque.offer("Tosho");
        String element = deque.poll();
        Assert.assertEquals("Gosho", element);
        Assert.assertEquals(2, deque.size());
    }

    @Test
    public void testRemoveFirst_ShouldRemoveValidElement(){
        fill();
        deque.push("Gosho");
        String element = deque.removeFirst();
        Assert.assertEquals("Gosho", element);
        Assert.assertEquals(3, deque.size());
    }
    @Test
    public void testRemoveFirst_ShouldReturnNullIfDequeIsEmpty(){
        String element = deque.removeFirst();
        Assert.assertNull(element);
        int size = deque.size();
        Assert.assertEquals(0, deque.size());
    }

    @Test
    public void testRemoveLast_ShouldReturnNullIfDequeIsEmpty(){
        String element = deque.removeLast();
        Assert.assertNull(element);
        int size = deque.size();
        Assert.assertEquals(0, deque.size());
    }

    @Test
    public void testPop_ShouldRemoveValidElement(){
        deque.push("Gosho");
        deque.push("Pesho");
        deque.push("Ivan");
        String element = deque.pop();
        Assert.assertEquals("Ivan", element);
        Assert.assertEquals(2, deque.size());
    }

    @Test
    public void testIterator_ShouldIterFromBeginToEnd(){
        fill();
        deque.add("Gosho");
        deque.add("Pesho");
        int index = 0;
        for (String s : deque) {
            Assert.assertEquals(s,deque.get(index++) );
        }
    }

    @Test
    public void testIterator_ShouldDoNothingIfDequeIsEmpty(){
        String flag = "work";
        for (String s : deque) {
            flag = s;
        }
        Assert.assertEquals("work", flag);
    }

    public void fill(){
        this.deque.addLast("0");
        this.deque.add("1");
        this.deque.offer("2");
    }
}