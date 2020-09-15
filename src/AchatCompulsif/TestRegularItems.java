package shopping;

import org.junit.Before;
import org.junit.Test;

import shopping.Cart;
import shopping.CartItem;
import shopping.Product;
import shopping.RegularItem;

import static org.junit.Assert.assertEquals;

public class TestRegularItems {

    private Product product1;
    private Product product2;
    private CartItem item1;
    private CartItem item2;

    @Before
    public void setUp() {
        product1 = new Product("Cheese", 300);
        item1 = new RegularItem(product1, 200);
        product2 = new Product("Bier", 150);
        item2 = new RegularItem(product2, 600);
    }

   
    @Test
    public void test_price() {
    	assertEquals(item1.unitPrice() * item1.getQuantity(), item1.price(),0.0);
    }

    @Test
    public void test_increaseQty() {
    	int previousQty = item1.getQuantity();
    	int delta = 5;
        item1.increaseQty(delta);
        assertEquals((previousQty + delta) * item1.unitPrice(), item1.price(),0.0);
    }

    @Test
    public void test_decreaseQty() {
    	int previousQty = item1.getQuantity();
    	int delta = 3;
        item1.decreaseQty(delta);
        assertEquals((previousQty - delta) * item1.unitPrice(), item1.price(),0.0);
    }
        
}
