package shopping;

public abstract class CartItem {
    private int quantity;
    private Product product;

    public CartItem(Product pdt, int qty){
        this.quantity = qty;
        this.product = pdt;
    }

    public Product getProduct(){
        return this.product;
    }

    public int getQuantity() {
        return quantity;
    }

    public int unitPrice(){
        return this.product.unitPrice();
    }

    public String toString(){
        return this.product.description();
    }

    public boolean decreaseQty(int moins){
        if (this.quantity - moins >= 0){
            this.quantity -= moins;
            return true;
        }
        return false;
    }

    public void increaseQty(int plus){
        this.quantity += plus;
    }

    public int price(){
        return 0;
    }
}
