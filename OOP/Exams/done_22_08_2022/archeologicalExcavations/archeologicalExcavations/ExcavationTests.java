package archeologicalExcavations;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ExcavationTests {

    private Archaeologist archaeologist;
    private Excavation excavation;

    @Before
    public void setup(){
        archaeologist = new Archaeologist("Gosho", 100);
        excavation = new Excavation("Unas", 1);

    }

    @Test (expected = NullPointerException.class)
    public void test_setName_Should_Throw_WhenNameIsInvalid(){
        excavation = new Excavation("   ", 7);
    }

    @Test
    public void test_setName_setCorrectName(){
        archaeologist = new Archaeologist("Gosho", 8);
        Assert.assertEquals("Gosho", archaeologist.getName());
    }

    @Test
    public void test_getName_getCorrectName(){

        Assert.assertEquals("Unas", excavation.getName());
    }

    @Test (expected = IllegalArgumentException.class)
    public void test_setCapacity_shouldThrowWhenCapacityIsLessThanZero(){
        excavation = new Excavation("Home", -1);
    }

    @Test
    public void test_setCapacity_setCorrectCapacity(){
        excavation = new Excavation("Home", 20);
        Assert.assertEquals(20, excavation.getCapacity());
    }

    @Test
    public void test_removeArchaeologist_returnFalseIfPersonIsNull(){
       boolean actual = excavation.removeArchaeologist(null);
       Assert.assertFalse(actual);
    }

    @Test
    public void test_removeArchaeologist_returnFalseIfPersonNotExist(){
        excavation.addArchaeologist(archaeologist);
        boolean actual = excavation.removeArchaeologist("Pesho");
        Assert.assertFalse(actual);
    }

    @Test
    public void test_removeArchaeologist_returnTrueIfPersonExist(){
        excavation.addArchaeologist(archaeologist);
        boolean actual = excavation.removeArchaeologist("Gosho");

        Assert.assertTrue(actual);
    }

    @Test (expected = IllegalArgumentException.class)
    public void test_addArchaeologist_ShouldThrowWhenCapacityIsFull(){
        excavation.addArchaeologist(archaeologist);
        Archaeologist another = new Archaeologist("Pesho", 7);
        excavation.addArchaeologist(another);
    }

    @Test (expected = IllegalArgumentException.class)
    public void test_addArchaeologist_ShouldThrowWhenPersonExist(){
        excavation = new Excavation("Grow", 7);
        excavation.addArchaeologist(archaeologist);
        excavation.addArchaeologist(archaeologist);
    }

    @Test
    public void test_addArchaeologist_ShouldAddAnotherPerson(){
        excavation = new Excavation("Grown", 5);
        excavation.addArchaeologist(archaeologist);
        Archaeologist another = new Archaeologist("Pesho", 7);
        excavation.addArchaeologist(another);

        Assert.assertEquals(2, excavation.getCount());

    }
}
