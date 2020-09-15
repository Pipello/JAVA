package shopping;

public class DegressiveItem extends CartItem {
    int thresholdQty;
    int reducedUnitPrice;
    public DegressiveItem(Product pdt, int qty, int thrsh, int reduce){
        super(pdt, qty);
        this.thresholdQty = thrsh;
        this.reducedUnitPrice = reduce;
    }
    @Override
    public int price(){
        if(this.getQuantity() >= this.thresholdQty){
            return this.reducedUnitPrice * this.getQuantity();
        }
        return this.unitPrice() * this.getQuantity();
    }
}
