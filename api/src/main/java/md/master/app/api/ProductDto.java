package md.master.app.api;


import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Product DTO model")
public class ProductDto {
    @Schema(description = "Product id", required = true,example = "1")
    private Long id;
    @Schema(description = "Product title", required = true,example = "Title 1234")
    private String title;
    @Schema(description = "Product price", required = true,example = "99.99")
    private BigDecimal price;
    @Schema(description = "Product category", required = true,example = "Other")
    private String categoryTitle;


    public ProductDto() {
    }

    public ProductDto(Long id, String title, BigDecimal price, String categoryTitle) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.categoryTitle = categoryTitle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }
}
