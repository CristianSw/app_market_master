package md.master.app.core.services;

import lombok.RequiredArgsConstructor;


import md.master.app.api.ProductDto;
import md.master.app.api.ResourceNotFoundException;
import md.master.app.core.entities.Category;
import md.master.app.core.entities.Product;
import md.master.app.core.repositories.ProductRepository;
import md.master.app.core.repositories.specifications.ProductSpecifications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public Page<Product> findAll(Specification<Product> spec, int page){
        return productRepository.findAll(spec, PageRequest.of(page,10));
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

    public Specification<Product> createSpecByFilters(Integer minPrice, Integer maxPrice, String title){
        Specification<Product> spec = Specification.where(null);
        if (minPrice != null){
            spec = spec.and(ProductSpecifications.priceGreaterOrEqualsThan(minPrice));
        }

        if (maxPrice != null){
            spec = spec.and(ProductSpecifications.priceLessOrEqualsThan(maxPrice));
        }

        if (title != null){
            spec = spec.and(ProductSpecifications.titleLike(title));
        }
        return spec;
    }
}

