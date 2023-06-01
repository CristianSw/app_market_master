package md.master.app.api;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Category DTO model")
public class CategoryDto {
    @Schema(description = "Category id", required = true, example = "1")
    private Long id;
    @Schema(description = "Category title", required = true, example = "ROLE_USER")
    private String title;
    @Schema(description = "List of products in cart", required = true)
    private List<ProductDto> products;


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

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }
}
