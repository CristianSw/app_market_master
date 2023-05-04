package md.master.app.app_market_master.services;

import lombok.RequiredArgsConstructor;
import md.master.app.app_market_master.dtos.ProductDto;
import md.master.app.app_market_master.entities.Category;
import md.master.app.app_market_master.entities.Product;
import md.master.app.app_market_master.exceptions.ResourceNotFoundException;
import md.master.app.app_market_master.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public List<Product> findAll(){
        return productRepository.findAll();
    }
    public Optional<Product> findById(Long id){
        return productRepository.findById(id);
    }

    public void deleteById(Long id){
        productRepository.deleteById(id);
    }

    public Product createNewProduct(ProductDto productDto){
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        Category category = categoryService.findByTitle(productDto.getCategoryTitle()).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        product.setCategory(category);
        productRepository.save(product);
        return product;
    }
}
