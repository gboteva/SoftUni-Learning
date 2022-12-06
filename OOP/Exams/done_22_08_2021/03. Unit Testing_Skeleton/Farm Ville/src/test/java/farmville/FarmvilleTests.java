package farmville;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FarmvilleTests {
    private Animal cat;
    private Animal dog;
    private Animal frog;
    private Farm farm;

    @Before
    public void setup(){
        this.cat = new Animal("Cat", 100);
        this.dog = new Animal("Dog", 200);
        this.frog = new Animal("Frog", 20);
        farm = new Farm("Name", 3);
    }

    @Test
    public void testGetName_ShouldReturnValidName(){
        Assert.assertEquals("Name", farm.getName());
    }

    @Test
    public void testGetCapacity_ShouldReturnValidValue(){
        int actual = farm.getCapacity();
        Assert.assertEquals(3, actual);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testAdd_ShouldThrowWhenFarmIsFull(){
        fillFarm();
        farm.add(new Animal("Chicken", 30));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testAdd_ShouldThrowWhenTryToAddExistingAnimal(){
        Farm farm2 = new Farm("House", 4);
        farm2.add(cat);
        farm2.add(dog);
        farm2.add(frog);
        farm2.add(cat);
    }

    @Test
    public void testAdd_ShouldAddSuccessfully(){
        fillFarm();
        Assert.assertEquals(3, farm.getCount());
    }

    @Test
    public void testRemove_ShouldReturnFalseIfAnimalDoesNotExist(){
        fillFarm();
        Assert.assertFalse(farm.remove("Rabbit"));
    }

    @Test
    public void testRemove_ShouldReturnTrueIfRemoveExistingAnimal(){
        fillFarm();
        Assert.assertTrue(farm.remove("Cat"));
        Assert.assertEquals(2, farm.getCount());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSetCapacity_ShouldThrowWhenGivenCapacityIsLowerThanZero(){
        Farm farm2 = new Farm("House", -2);
    }

    @Test(expected = NullPointerException.class)
    public void testSetName_ShouldThrowWhenGivenNameIsEmpty(){
        Farm farm3 = new Farm("    ", 3);
    }

    @Test(expected = NullPointerException.class)
    public void testSetName_ShouldThrowWhenGivenNameIsNull(){
        Farm farm3 = new Farm(null, 3);
    }

    @Test
    public void testGetEnergy_ShouldReturnValidValue(){
        Assert.assertEquals(String.valueOf(100.0), String.valueOf(cat.getEnergy()));
    }

    private void fillFarm() {
        farm.add(cat);
        farm.add(dog);
        farm.add(frog);
    }
}
