package md.master.app.app_market_master.convertors;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import md.master.app.app_market_master.dtos.ProductDto;
import md.master.app.app_market_master.entities.Category;
import md.master.app.app_market_master.entities.Product;
import md.master.app.app_market_master.exceptions.ResourceNotFoundException;
import md.master.app.app_market_master.services.CategoryService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductConverter {
    private final CategoryService categoryService;
    public ProductDto entityToDto(Product product){
        return new ProductDto(product.getId(),product.getTitle(),product.getPrice(),product.getCategory().getTitle());
    }
    public Product dtoToEntity(ProductDto productDto){
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        Category category = categoryService.findByTitle(productDto.getCategoryTitle()).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        product.setCategory(category);
        product.setPrice(productDto.getPrice());
        return product;
    }
}
