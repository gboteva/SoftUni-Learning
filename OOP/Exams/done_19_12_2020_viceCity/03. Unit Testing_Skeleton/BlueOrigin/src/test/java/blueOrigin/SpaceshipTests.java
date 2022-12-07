package blueOrigin;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SpaceshipTests {
    private Astronaut astronaut;
    private Astronaut astronaut2;
    private Spaceship spaceship;

    @Before
   public void setup(){
        astronaut = new Astronaut("Gosho", 6);
        astronaut2 = new Astronaut("Pesho", 3);
        spaceship = new Spaceship("A", 2);
    }

    @Test(expected = NullPointerException.class)
    public void test_constructor_shouldThrowWhenNameIsNull(){
        Spaceship spaceship = new Spaceship(null, 7);
    }

    @Test(expected = NullPointerException.class)
    public void test_constructor_shouldThrowWhenNameIsEmpty(){
        Spaceship spaceship = new Spaceship("   ", 7);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_constructor_shouldThrowWhenCapacityIsNegative(){
        Spaceship spaceship = new Spaceship("null", -7);
    }

    @Test
    public void test_constructor_shouldCreateValidObject(){
        Assert.assertEquals("A", spaceship.getName());
        Assert.assertEquals(0, spaceship.getCount());
        Assert.assertEquals(2, spaceship.getCapacity());
    }

    @Test (expected =  IllegalArgumentException.class)
    public void test_add_shouldThrowWhenAstronautExists(){
        spaceship.add(astronaut);
        spaceship.add(astronaut);
    }

    @Test (expected =  IllegalArgumentException.class)
    public void test_add_shouldThrowWhenCapacityIsFull(){
        spaceship.add(astronaut);
        spaceship.add(astronaut2);
        spaceship.add(new Astronaut("Ivan", 6));
    }

    @Test
    public void test_add_shouldAdd(){
        spaceship.add(astronaut);
        spaceship.add(astronaut2);
        Assert.assertEquals(2, spaceship.getCount());
    }

    @Test
    public void test_remove_shouldRemove(){
        spaceship.add(astronaut);
        spaceship.add(astronaut2);
        boolean isRemoved = spaceship.remove("Pesho");
        Assert.assertEquals(1, spaceship.getCount());
        Assert.assertTrue(isRemoved);
    }

    @Test
    public void test_remove_shouldNotRemoveIfAstronautDoesNotExist(){
        spaceship.add(astronaut);
        spaceship.add(astronaut2);
        boolean isRemoved = spaceship.remove("Ivan");
        Assert.assertEquals(2, spaceship.getCount());
        Assert.assertFalse(isRemoved);
    }
}
