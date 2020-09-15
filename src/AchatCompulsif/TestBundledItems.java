package shopping;

import org.junit.Before;
import org.junit.Test;

import shopping.BundledItem;
import shopping.Cart;
import shopping.CartItem;
import shopping.Product;

import static org.junit.Assert.assertEquals;

public class TestBundledItems {

    private Product product;
    private int bundleQuantity;
    private int paidQuantity;

    @Before
    public void setUp() {
        bundleQuantity = 3;
        paidQuantity = 2;
        product = new Product("something", 1000);
    }

    @Test
    public void test_buyThatManyGetSomeFree() {
    	BundledItem bundledItem = new BundledItem(product, bundleQuantity, bundleQuantity, paidQuantity);
    	assertEquals(paidQuantity * bundledItem.unitPrice(), bundledItem.price(),0.0);
    }

    @Test
    public void test_multipleBundles() {
        int howManyBundles = 5;
    	BundledItem bundledItem = new BundledItem(product, bundleQuantity * howManyBundles, bundleQuantity, paidQuantity);
        assertEquals(howManyBundles * paidQuantity * bundledItem.unitPrice(), bundledItem.price(), 0.0);
    }

    @Test
    public void test_belowBundleGetNormalPrice() {
        int howMany = bundleQuantity - 1;
        BundledItem bundledItem = new BundledItem(product, howMany, bundleQuantity, paidQuantity);
        assertEquals(howMany * bundledItem.unitPrice(), bundledItem.price(), 0.0);
    }

    @Test
    public void test_incompleteBundleGetNormalPrice() {
        int oneOverFiveBundles = 5 * bundleQuantity + 1;
        BundledItem bundledItem = new BundledItem(product, oneOverFiveBundles, bundleQuantity, paidQuantity);
        assertEquals((5 * paidQuantity + 1) * bundledItem.unitPrice(), bundledItem.price(), 0.0);
    }
}
