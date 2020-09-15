package shopping;

public class ReducedPriceItem extends CartItem{
    float priceFactor;
    public ReducedPriceItem(Product pdt, int qty, float prcFact){
        super(pdt, qty);
        this.priceFactor = 1-(prcFact/100);
    }
    @Override
    public int price(){
        return (int) (this.getQuantity() * (this.unitPrice()*this.priceFactor));
    }
}
