package md.master.app.carts.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import md.master.app.api.ProductDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class Cart {
    private List<CartItem> items;
    private BigDecimal totalPrice;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }


    public void add(ProductDto product) {
        for (CartItem item : items) {
            if (product.getId().equals(item.getProductId())) {
                item.changeQuantity(1);
                recalculate();
                return;
            }
        }
        items.add(new CartItem(product.getId(), product.getTitle(), 1, product.getPrice(), product.getPrice()));
        recalculate();
    }

    public void clear(){
        items.clear();
        totalPrice = BigDecimal.ZERO;
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
        totalPrice = BigDecimal.ZERO;
        for (CartItem item : items) {
            totalPrice = totalPrice.add(item.getPrice());
        }
    }
}

