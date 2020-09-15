package shopping;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    List<CartItem> cartList;
    public Cart(){
        this.cartList = new ArrayList<CartItem>();
    }
    public void clear(){
        this.cartList.clear();
    }

    public void add(CartItem item){
        this.cartList.add(item);
    }

    public void remove(CartItem item){
        this.cartList.remove(item);
    }

    public int total(){
        int total = 0;
        for (CartItem item: this.cartList) {
            total += item.price();
        }
        return total;
    }

    public int quantity(Product p){
        return 0;
    }

    public List priceGreater(int price){
        return new ArrayList<CartItem>();
    }

    public String toString(){
        String bisnounours = "";
        bisnounours += "--------------------";

        bisnounours += "--------------------";
        return bisnounours;
    }
}
