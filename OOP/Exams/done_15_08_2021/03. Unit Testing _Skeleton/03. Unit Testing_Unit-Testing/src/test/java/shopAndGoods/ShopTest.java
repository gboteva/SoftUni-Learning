package shopAndGoods;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class ShopTest {
    private final static String unexistingShelf = "Shelves13";
    private final static String existShelf = "Shelves1";

    private Shop shop;
    private Goods goods;
    private Goods goods2;

    @Before
    public void setup() {
        shop = new Shop();
        goods = new Goods("box", "35");
        goods2 = new Goods("toy", "43");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void test_TestGetShelves_ShouldReturnUnmodifiableCollection() {
        shop.getShelves().put("box", goods2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddGods_ShouldThrowWhenShelfDoesNotExist() throws OperationNotSupportedException {
        shop.addGoods(unexistingShelf, goods);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddGods_ShouldThrowWhenShelfIsTaken() throws OperationNotSupportedException {
        shop.addGoods(existShelf, goods);
        shop.addGoods(existShelf, goods2);
    }


    @Test
    public void testAddGods_ShouldThrowWhenShopContainsGoods() {
        try {
            shop.addGoods(existShelf, goods);
        } catch (OperationNotSupportedException e) {
            Assert.assertEquals("Goods is already in shelf!", e.getMessage());
        }
    }

    @Test
    public void testAddGood_ShouldAddGivenGoodsAtTheEmptyShelf() throws OperationNotSupportedException {
        shop.addGoods(existShelf, goods2);
        Assert.assertEquals(shop.getShelves().get(existShelf), goods2);
        Goods actual = shop.getShelves().get(existShelf);
        Assert.assertEquals(actual.getName(), "toy");
        Assert.assertEquals(actual.getGoodsCode(), "43");
    }

    @Test
    public void testAddGoods_ShouldReturnValidString() throws OperationNotSupportedException {
        String actual = shop.addGoods(existShelf, goods2);
        String expected = "Goods: 43 is placed successfully!";
        Assert.assertEquals(actual, expected);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveGoods_ShouldThrowWhenShelfDoesNotExist() {
        shop.removeGoods(unexistingShelf, goods);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveGoods_ShouldThrowWhenTryToRemoveInvalidGoods() throws OperationNotSupportedException {
        shop.addGoods(existShelf, goods);
        shop.removeGoods(existShelf, goods2);
    }

    @Test
    public void testRemoveGoods_ShouldPutNullAtTheShelf() throws OperationNotSupportedException {
        shop.addGoods(existShelf, goods);
        shop.removeGoods(existShelf, goods);

        Assert.assertNull(shop.getShelves().get(existShelf));
    }

    @Test
    public void testRemoveGoodsShouldReturnValidString() throws OperationNotSupportedException {
        shop.addGoods(existShelf, goods);
        String actual = shop.removeGoods(existShelf, goods);
        String expected = "Goods: 35 is removed successfully!";
        Assert.assertEquals(expected, actual);
    }


}