package md.master.app.app_market_master.dtos;

import lombok.Data;
import md.master.app.app_market_master.entities.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Data
public class Cart {
    private List<CartItem> items;
    private int totalPrice;
    private boolean presentFlag;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }


    public void add(Product product) {
        for (CartItem item : items) {
            if (product.getId().equals(item.getProductId())) {
                item.changeqUantity(1);
                recalculate();
                return;
            }
        }
        items.add(new CartItem(product.getId(), product.getTitle(), 1, product.getPrice(), product.getPrice()));
        recalculate();
    }

    public void clear(){
        items.clear();
        totalPrice = 0;
    }

    public void remove(Long productId){
        if (items.removeIf(item -> item.getProductId().equals(productId))) {
            recalculate();
        }
    }
    public void increaseQuantity(Long productId){
        for (CartItem item : items) {
            if (item.getProductId().equals(productId)) {
                item.setQuantity(item.getQuantity() + 1);
            }
        }
        recalculate();
    }

    public void decreaseQuantity(Long productId){
        for (CartItem item : items) {
            if (item.getProductId().equals(productId)) {
                if (item.getQuantity() > 1) {
                    item.setQuantity(item.getQuantity() - 1);
                }
            }
        }
        recalculate();
    }

    private void recalculate() {
        totalPrice = 0;
        for (CartItem item : items) {
            totalPrice += item.getPricePerProduct() * item.getQuantity();
            item.setPrice(item.getPricePerProduct() * item.getQuantity());
        }
    }
}

