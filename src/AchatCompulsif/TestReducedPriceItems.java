package shopping;

import org.junit.Before;
import org.junit.Test;

import shopping.Cart;
import shopping.CartItem;
import shopping.Product;
import shopping.ReducedPriceItem;

import static org.junit.Assert.assertEquals;

public class TestReducedPriceItems {

    private Product product;

    @Before
    public void setUp() {
        product = new Product("something", 1000);
    }

    @Test
    public void test_promotionAppliesToOne() {
        CartItem reducedItem = new ReducedPriceItem(product, 1, 15);
        assertEquals(850, reducedItem.price(), 0.0);
    }
}
