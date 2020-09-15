package shopping;

public class Product {
    private int unitPrice;
    private String description;

    public Product(String desc, int p){
        this.unitPrice = p;
        this.description = desc;
    }

    public int unitPrice() {
        return unitPrice;
    }

    public String description() {
        return description;
    }
}
