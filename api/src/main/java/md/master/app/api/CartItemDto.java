package md.master.app.api;


import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
@Schema(description = "Cart DTO model")
public class CartItemDto {
    @Schema(description = "Product id", required = true, example = "1")
    private Long productId;
    @Schema(description = "Product title", required = true, example = "title")
    private String productTitle;
    @Schema(description = "Product quantity", required = true, example = "3")
    private int quantity;
    @Schema(description = "Product price per product", required = true, example = "100")
    private BigDecimal pricePerProduct;
    @Schema(description = "Product price for all products, its take in consideration quantity", required = true, example = "300")
    private BigDecimal price;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
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
