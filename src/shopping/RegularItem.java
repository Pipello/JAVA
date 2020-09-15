package shopping;

public class RegularItem extends CartItem {
    public RegularItem(Product pdt, int qty){
        super(pdt, qty);
    }
    @Override
    public int price(){
        return this.unitPrice() * this.getQuantity();
    }
    @Override
    public String toString(){
        String retour = "";
        retour = "Regular Item:" + this.getProduct().description() + " qt=" + this.getQuantity();
        return retour;
    }
}
