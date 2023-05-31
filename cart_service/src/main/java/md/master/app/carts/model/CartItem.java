package md.master.app.carts.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItem {
    private Long productId;
    private String productTitle;
    private int quantity;
    private BigDecimal pricePerProduct;
    private BigDecimal price;




    public void changeQuantity(int delta){
        quantity+= delta;
        price = pricePerProduct .multiply(BigDecimal.valueOf(quantity));
    }

}
