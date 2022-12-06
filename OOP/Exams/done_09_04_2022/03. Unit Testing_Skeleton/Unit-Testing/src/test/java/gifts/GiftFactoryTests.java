package gifts;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class GiftFactoryTests {
    private Gift gift;
    private GiftFactory giftFactory;

    @Before
    public void setup(){
        gift =  new Gift("game", 4.4);
        giftFactory  = new GiftFactory();
    }

    @Test
    public void testCreateGift_ShouldRAddGiftToList(){
        giftFactory.createGift(gift);
        giftFactory.createGift(new Gift("ball", 3.3));
        int actual = giftFactory.getCount();
        Assert.assertEquals(2, actual);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testCreateGift_ShouldThrowWhenGiftAlreadyExist(){
        giftFactory.createGift(gift);
        giftFactory.createGift(gift);
    }

    @Test
    public void testRemoveGiftShouldRemoveExistingGift(){
        giftFactory.createGift(gift);
        boolean actual = giftFactory.removeGift(gift.getType());
        Assert.assertTrue(actual);
    }

    @Test
    public void testRemoveGift_ShouldReturnFalseIfGiftNotPresent(){
        giftFactory.createGift(gift);
        boolean actual = giftFactory.removeGift("ball");
        Assert.assertFalse(actual);
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveGift_ShouldThrowWhenNameIsNull(){
        boolean actualNull = giftFactory.removeGift(null);
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveGift_ShouldThrowWhenNameIsEmpty(){
        boolean actualNull = giftFactory.removeGift("        ");
    }

    @Test
    public void testGetPresentWithLeastMagic_ShouldReturnRealGift(){
        giftFactory.createGift(gift);
        giftFactory.createGift(new Gift("ball", 3.3));

        Gift actual = giftFactory.getPresentWithLeastMagic();
        Assert.assertEquals(String.valueOf(actual.getMagic()), "3.3");

    }
    @Test
    public void testGetPresentWithLeastMagic_ShouldNullWhenFactoryIsEmpty(){
        Gift gift = giftFactory.getPresentWithLeastMagic();
        Assert.assertNull(gift);

    }

    @Test
    public void testGetPresent_ShouldReturnAddedGiftInTheFactory(){
        giftFactory.createGift(gift);
        giftFactory.createGift(new Gift("ball", 3.3));

        Gift actualGift = giftFactory.getPresent("game");

        Assert.assertEquals("game", actualGift.getType());
    }

    @Test
    public void testGetPresent_ShouldReturnNullWhenFactoryIsEmpty(){

        Gift actualGift = giftFactory.getPresent("game");

        Assert.assertNull(actualGift);
    }

    @Test (expected = UnsupportedOperationException.class)
    public void testGetPresents_ShouldReturnUnmodifiableList(){
        giftFactory.createGift(gift);
        Collection<Gift> presents = giftFactory.getPresents();
        presents.add(gift);
    }

}
