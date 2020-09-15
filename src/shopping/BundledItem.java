package shopping;

public class BundledItem extends CartItem {
    int bundleSize;
    int paidQuantity;
    public BundledItem(Product pdt, int qty, int bundleSize, int paidQuantity) {
        super(pdt, qty);
        this.bundleSize = bundleSize;
        this.paidQuantity = paidQuantity;
    }

    @Override
    public int price() {
        int quantityLeft = this.getQuantity()%this.bundleSize;
        return ((this.getQuantity()-quantityLeft)/this.bundleSize)*this.paidQuantity*this.unitPrice() + quantityLeft * this.unitPrice();
    }
}