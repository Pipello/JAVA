package shopping;

import org.junit.Before;
import org.junit.Test;

import shopping.Cart;
import shopping.DegressiveItem;

import static org.junit.Assert.assertEquals;

public class TestDegressiveItems {

    private Product product;
    private int threshold;
    private int reducedPrice;

    @Before
    public void setUp() {
        threshold = 10;
        reducedPrice = 800;
        product = new Product("something", 1000);
    }

    @Test
    public void test_belowThresholdAskBasePrice() {
        int quantity = threshold - 1;
        CartItem degressiveItem = new DegressiveItem(product, quantity, threshold, reducedPrice);
        assertEquals(quantity * degressiveItem.unitPrice(), degressiveItem.price(), 0.0);
    }

    @Test
    public void test_overThresholdAskReducedPrice() {
        int quantity = threshold * 2;
        CartItem degressiveItem = new DegressiveItem(product, quantity, threshold, reducedPrice);
        assertEquals(quantity * reducedPrice, degressiveItem.price(), 0.0);
    }

    @Test
    public void test_atThresholdAskReducedPrice() {
        int quantity = threshold;
        CartItem degressiveItem = new DegressiveItem(product, quantity, threshold, reducedPrice);
        assertEquals(quantity * reducedPrice, degressiveItem.price(), 0.0);
    }
}
