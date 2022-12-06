package cats;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HouseTests {

    private Cat cat;
    private Cat cat2;
    private Cat cat3;
    private House house;

    @Before
    public void setup(){
        this.cat = new Cat("Gosho");
        this.cat2 = new Cat("Ceca");
        this.cat3 = new Cat("Pesho");
        this.house = new House("Home", 2);
    }

    @Test (expected = NullPointerException.class)
    public void test_constructor_ShouldThrowWhenNameIsNull(){
        House house = new House(null, 3);
    }

    @Test (expected = NullPointerException.class)
    public void test_constructor_ShouldThrowWhenNameIsEmpty(){
        House house = new House("   ", 3);
    }

    @Test (expected = IllegalArgumentException.class)
    public void test_constructor_ShouldThrowWhenCapacityIsNegative(){
        House house = new House("Home", -3);
    }

    @Test (expected = IllegalArgumentException.class)
    public void test_addCat_shouldThrowWhenNoSpaceToAdd(){
        house.addCat(cat);
        house.addCat(cat2);
        house.addCat(cat3);
    }
    @Test
    public void testConstructor_ShouldInitializeObject(){
        house = new House("Home", 5);
        Assert.assertEquals("Home", house.getName());
        Assert.assertEquals(5, house.getCapacity());
    }

    @Test
    public void test_addCat_ShouldAddCorrect(){
        house.addCat(cat);
        house.addCat(cat2);
        Assert.assertEquals(2, house.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_removeCat_ShouldThrowWhenCatDoesNotExists(){
        house.addCat(cat);
        house.removeCat("Ceca");
    }

    @Test
    public void test_removeCat_ShouldRemove(){
        house.addCat(cat);
        house.addCat(cat2);
        Assert.assertEquals(2, house.getCount());

        house.removeCat("Gosho");
        Assert.assertEquals(1, house.getCount());
    }

    @Test (expected = IllegalArgumentException.class)
    public void test_catForSale_ShouldThrowWhenCatDoesNotExists(){
        house.addCat(cat);
        house.addCat(cat2);
        house.catForSale("Pesho");
    }

    @Test
    public void test_catForSale_ShouldReturnValidCat(){
        house.addCat(cat);
        house.addCat(cat2);
        Assert.assertTrue(cat2.isHungry());
        Cat actual = house.catForSale("Ceca");
        Assert.assertFalse(actual.isHungry());
    }

    @Test
    public void test_statistics_ShouldReturnValidString(){
        house.addCat(cat);
        house.addCat(cat2);
        String expected ="The cat Gosho, Ceca is in the house Home!";
        Assert.assertEquals(expected, house.statistics());
    }

}
