package shopping;

import org.junit.Before;
import org.junit.Test;

import shopping.Cart;
import shopping.RegularItem;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestCart {

    private Cart cart;
    private Product pizza, sauce, pasta;
	private int threshold;
	private int reducedPrice;
	private Product product;
	private int bundleQuantity;
	private int paidQuantity;

    
    @Before
    public void setUp() {
        cart = new Cart();
        pizza = new Product("pizza", 650);
        sauce = new Product("tomate basilic", 350);
        pasta = new Product("spaghetti", 250);
        
        threshold = 10;
        reducedPrice = 800;
        
        bundleQuantity = 3;
        paidQuantity = 2;
        
        cart = new Cart();
        product = new Product("something", 1000);
    }

    
    @Test
    public void test_emptyCartCostsZero() {
        assertEquals(0.0, cart.total(),0.0);
    }

    
    @Test
    public void test_addClear() {
    	cart.add(new RegularItem(pizza, 1));
    	cart.add(new RegularItem(sauce, 2));
    	cart.clear();
    	assertEquals(0.0, cart.total(),0.0);
    }
    
    @Test
    public void test_addRemove() {
    	CartItem item1 = new RegularItem(pizza, 3);
    	CartItem item2 = new RegularItem(pasta, 1);
    	cart.clear();
    	cart.add(item1);
    	cart.add(item2);
    	cart.remove(item1);
    	assertEquals(pasta.unitPrice(), cart.total(),0.0);    	
    }
    
    @Test
    public void test_singleRegularItemCostsItsUnitPrice() {
    	CartItem item1 = new RegularItem(pizza, 3);
    	cart.clear();
        cart.add(item1);
        assertEquals(item1.unitPrice() * item1.getQuantity(), cart.total(),0.0);
    }

    @Test
    public void test_multipleRegularItemItemsCostProportionally() {
    	CartItem item1 = new RegularItem(pizza, 3);
    	CartItem item2 = new RegularItem(pasta, 1);
        cart.clear();
        cart.add(item1);
    	cart.add(item2);
        double sum = (item1.getQuantity() * item1.unitPrice()) + (item2.getQuantity() * item2.unitPrice());
        assertEquals(sum, cart.total(),0.0);
    }

    @Test
    public void test_separatelyAdding() {
        int howMany = 3;
        double unitPrice = pizza.unitPrice();
        cart.clear();
        for (int i = howMany; i > 0; i--) {
            cart.add(new RegularItem(pizza, 1));
        }
        assertEquals(howMany * unitPrice, cart.total(),0.0);
    }
    
    @Test
    public void test_quantityProduct() {
    	int qty1 = 3;
    	int qty2 = 5;
    	CartItem item1 = new RegularItem(pizza, qty1);
    	CartItem item2 = new RegularItem(pizza, qty2);
    	cart.clear();
    	cart.add(item1);
    	cart.add(item2);
    	assertEquals(qty1 + qty2, cart.quantity(pizza));
    }
    
    @Test
    public void test_DegressiveItem_belowThresholdAskBasePrice() {
        int quantity = threshold - 1;
        CartItem degressiveItem = new DegressiveItem(product, quantity, threshold, reducedPrice);
        cart.clear();
        cart.add(degressiveItem);
        assertEquals(quantity * degressiveItem.unitPrice(), cart.total(), 0.0);
    }

    @Test
    public void test_DegressiveItem__overThresholdAskReducedPrice() {
        int quantity = threshold * 2;
        CartItem degressiveItem = new DegressiveItem(product, quantity, threshold, reducedPrice);
        cart.clear();
        cart.add(degressiveItem);
        assertEquals(quantity * reducedPrice, cart.total(), 0.0);
    }

    @Test
    public void test_DegressiveItem_atThresholdAskReducedPrice() {
        int quantity = threshold;
        CartItem degressiveItem = new DegressiveItem(product, quantity, threshold, reducedPrice);
        cart.clear();
        cart.add(degressiveItem);
        assertEquals(quantity * reducedPrice, cart.total(), 0.0);
    }
    
    
    @Test
    public void test_BundledItem_buyThatManyGetSomeFree() { 	
        BundledItem bundledItem = new BundledItem(product, bundleQuantity, bundleQuantity, paidQuantity);
        cart.clear();
        cart.add(bundledItem);
        assertEquals(paidQuantity * bundledItem.unitPrice(), cart.total(),0.0);
    }

    @Test
    public void test_BundledItem_multipleBundles() {
        int howManyBundles = 5;
    	BundledItem bundledItem = new BundledItem(product, bundleQuantity * howManyBundles, bundleQuantity, paidQuantity);
    	cart.clear();
        cart.add(bundledItem);
        assertEquals(howManyBundles * paidQuantity * bundledItem.unitPrice(), cart.total(), 0.0);
    }

    @Test
    public void test_BundledItem_belowBundleGetNormalPrice() {
        int howMany = bundleQuantity - 1;
        BundledItem bundledItem = new BundledItem(product, howMany, bundleQuantity, paidQuantity);
        cart.clear();
        cart.add(bundledItem);
        assertEquals(howMany * bundledItem.unitPrice(), cart.total(), 0.0);
    }
    
    @Test
    public void test_BundledItem_incompleteBundleGetNormalPrice() {
        int oneOverFiveBundles = 5 * bundleQuantity + 1;
        BundledItem bundledItem = new BundledItem(product, oneOverFiveBundles, bundleQuantity, paidQuantity);
        cart.clear();
        cart.add(bundledItem);
        assertEquals((5 * paidQuantity + 1) * bundledItem.unitPrice(), bundledItem.price(), 0.0);
    }
    
    @Test
    public void test_ReducedPriceItem_promotionAppliesToOne() {
        CartItem reducedItem = new ReducedPriceItem(product, 1, 15);
        cart.clear();
        cart.add(reducedItem);
        assertEquals(850, cart.total(), 0.0);
    }
    
    @Test 
    public void test_priceGreater() {
    	int qty = 10;
    	CartItem item1 = new RegularItem(pizza, qty);
    	CartItem item2 = new RegularItem(pasta, qty);  	
    	cart.clear();
    	cart.add(item1);
    	cart.add(item2);
    	assertTrue(cart.priceGreater(6000).contains(item1));
    }
    
    @Test
    public void test_printEmpty() {
        assertEquals("----------------------------------------------------------------\n" +
                "----------------------------------------------------------------\n" +
                "                  TOTAL:\t    0.00e", cart.toString());
    }

  @Test
    public void test_printOneOfEach() {
        cart.add(new RegularItem(sauce, 1));
        cart.add(new RegularItem(pizza, 1));
        cart.add(new RegularItem(pasta, 1));
        assertEquals("----------------------------------------------------------------\n" +
        		"Regular Item:tomate basilic  1 x 3.50e\t    3.50e\n" + 
        		"Regular Item:pizza  1 x 6.50e\t    6.50e\n" + 
        		"Regular Item:spaghetti  1 x 2.50e\t    2.50e\n" + 
                "----------------------------------------------------------------\n" +
                "                  TOTAL:\t   12.50e", cart.toString());
    } 
}
