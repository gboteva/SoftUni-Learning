package aquarium;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AquariumTests {
  private Fish fish;
  private Aquarium aquarium;

  @Before
    public void setup(){
      this.fish = new Fish("Nemo");
      this.aquarium = new Aquarium("Aqua", 3);
  }

  @Test
  public void test_isAvailable_ShouldReturnValidValue(){
    Assert.assertTrue(fish.isAvailable());
  }

  @Test
  public void testConstructor_ShouldCreateValidObject(){
    Assert.assertEquals("Aqua", aquarium.getName());
    Assert.assertEquals(3, aquarium.getCapacity());
    Assert.assertEquals(0, aquarium.getCount());
  }

  @Test
    public void testGetName_ShouldReturnValidName(){
      Assert.assertEquals("Aqua", aquarium.getName());
  }

  @Test (expected = IllegalArgumentException.class)
  public void test_Constructor_ShouldThrowWhenCapacityIsNegative(){
    Aquarium aquarium = new Aquarium("Aqua2", -3);
  }

  @Test (expected = NullPointerException.class)
  public void testConstructor_ShouldThrowWhenNameIsNull(){
    aquarium = new Aquarium(null, 3);
  }

  @Test (expected = NullPointerException.class)
  public void testConstructor_ShouldThrowWhenNameIsEmpty(){
    aquarium = new Aquarium("       ", 3);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testAdd_ShouldThrowWhenNoCapacityToAddNewFish(){
    aquarium.add(fish);
    aquarium.add(new Fish("Gosho"));
    aquarium.add(new Fish("Ivan"));
    aquarium.add(new Fish("Pesho"));
  }

  @Test
  public void testAdd_ShouldAddSuccessfully(){
    aquarium.add(fish);
    Assert.assertEquals(1, aquarium.getCount());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testRemove_ShouldThrowWhenTryToRemoveNonExistentFish(){
    aquarium.add(fish);
    aquarium.remove("Gosho");
  }

  @Test
  public void testRemove_ShouldRemoveExistFish(){
    aquarium.add(fish);
    aquarium.remove("Nemo");
    Assert.assertEquals(0, aquarium.getCount());
  }

  @Test (expected =  IllegalArgumentException.class)
  public void testSellFishShouldThrowWhenTryToSellUnExistentFish(){
    aquarium.add(fish);
    aquarium.sellFish("Gosho");
  }

  @Test
  public void testSellFish_ShouldSellExistentFish(){
    aquarium.add(fish);
    Fish soldFish = aquarium.sellFish("Nemo");

    Assert.assertEquals("Nemo", soldFish.getName());
    Assert.assertFalse(soldFish.isAvailable());

  }

  @Test
  public void testReport_ShouldReturnValidString(){
    aquarium.add(fish);
    aquarium.add(new Fish ("Gosho"));
    aquarium.add(new Fish("Pesho"));

    String expected = "Fish available at Aqua: Nemo, Gosho, Pesho";
    Assert.assertEquals(expected, aquarium.report());
  }


}

