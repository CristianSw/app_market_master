package md.master.app.app_market_master.convertors;

import lombok.RequiredArgsConstructor;
import md.master.app.app_market_master.dtos.CategoryDto;
import md.master.app.app_market_master.dtos.ProductDto;
import md.master.app.app_market_master.entities.Category;
import md.master.app.app_market_master.entities.Product;
import md.master.app.app_market_master.exceptions.ResourceNotFoundException;
import md.master.app.app_market_master.services.CategoryService;
import md.master.app.app_market_master.services.ProductService;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor 
public class CategoryConverter {
    private final CategoryService categoryService;
    private final ProductService productService;
    private final ProductConverter productConverter;
    public CategoryDto entityToDto(Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setTitle(categoryDto.getTitle());
        categoryDto.setProducts(category.getProducts().stream().map(productConverter::entityToDto).collect(Collectors.toList()));
        return categoryDto;
    }
//    public Category dtoToEntity(CategoryDto categoryDto){
//        Category category = new Category();
//        category.setId(category.getId());
//        category.setTitle(categoryDto.getTitle());
//        Product product = productService.findById(categoryDto.getProducts().).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
//        product.setCategory(product);
//        product.setPrice(productDto.getPrice());
//        return product;
//    }
}
