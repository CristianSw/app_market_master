package md.master.app.api;


import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Schema(description = "Cart DTO model")
public class CartDto {
    @Schema(description = "Cart items", required = true)
    private List<CartItemDto> items;
    @Schema(description = "Total price of cart", required = true,example = "999.00")
    private BigDecimal totalPrice;

    public List<CartItemDto> getItems() {
        return items;
    }

    public void setItems(List<CartItemDto> items) {
        this.items = items;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}

