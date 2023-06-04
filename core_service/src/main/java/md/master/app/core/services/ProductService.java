package md.master.app.core.services;

import lombok.RequiredArgsConstructor;


import md.master.app.api.ProductDto;
import md.master.app.api.ResourceNotFoundException;
import md.master.app.core.entities.Category;
import md.master.app.core.entities.Product;
import md.master.app.core.repositories.ProductRepository;
import md.master.app.core.repositories.specifications.ProductsSpecifications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    //    public Page<Product> findAll(Specification<Product> spec, int page){
//        return productRepository.findAll(spec, PageRequest.of(page,10));
//    }
    public Page<Product> findAll(Integer minPrice, Integer maxPrice, String partTitle, Integer page) {
        Specification<Product> spec = Specification.where(null);
        if (minPrice != null) {
            spec = spec.and(ProductsSpecifications.priceGreaterOrEqualsThan(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductsSpecifications.priceLessThanOrEqualsThan(maxPrice));
        }
        if (partTitle != null) {
            spec = spec.and(ProductsSpecifications.titleLike(partTitle));
        }

        return productRepository.findAll(spec, PageRequest.of(page - 1, 5));
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public Product createNewProduct(ProductDto productDto) {
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        Category category = categoryService.findByTitle(productDto.getCategoryTitle()).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        product.setCategory(category);
        productRepository.save(product);
        return product;
    }

//    public Specification<Product> createSpecByFilters(Integer minPrice, Integer maxPrice, String title) {
//        Specification<Product> spec = Specification.where(null);
//        if (minPrice != null) {
//            spec = spec.and(ProductsSpecifications.priceGreaterOrEqualsThan(minPrice));
//        }
//
//        if (maxPrice != null) {
//            spec = spec.and(ProductsSpecifications.priceLessOrEqualsThan(maxPrice));
//        }
//
//        if (title != null) {
//            spec = spec.and(ProductsSpecifications.titleLike(title));
//        }
//        return spec;
//    }
}

