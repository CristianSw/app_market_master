package md.master.app.api;


import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Order Item DTO model")
public class OrderItemDto {
    @Schema(description = "Order id", required = true,example = "1")
    private Long id;
    @Schema(description = "product title mapped to this order item", required = true,example = "CPU")
    private String productTitle;
    @Schema(description = "Order id", required = true,example = "1")
    private Long orderId;
    @Schema(description = "Product quantity", required = true,example = "3")
    private int quantity;
    @Schema(description = "Product price per product", required = true,example = "100.00")
    private BigDecimal pricePerProduct;
    @Schema(description = "Order item price", required = true,example = "300")
    private BigDecimal price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPricePerProduct() {
        return pricePerProduct;
    }

    public void setPricePerProduct(BigDecimal pricePerProduct) {
        this.pricePerProduct = pricePerProduct;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
