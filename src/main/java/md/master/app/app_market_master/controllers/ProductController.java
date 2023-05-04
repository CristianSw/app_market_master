package md.master.app.app_market_master.controllers;

import lombok.RequiredArgsConstructor;
import md.master.app.app_market_master.convertors.ProductConverter;
import md.master.app.app_market_master.dtos.ProductDto;
import md.master.app.app_market_master.entities.Category;
import md.master.app.app_market_master.entities.Product;
import md.master.app.app_market_master.exceptions.ResourceNotFoundException;
import md.master.app.app_market_master.services.CategoryService;
import md.master.app.app_market_master.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductConverter productConverter;


    @GetMapping
    public List<ProductDto> findAllProducts() {
        return productService.findAll().stream().map(productConverter::entityToDto ).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductDto findProductById(@PathVariable Long id) {
        Product p = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found,id =" + id));
        return productConverter.entityToDto(p);
    }

    @PostMapping
    public ProductDto createNewProduct(@RequestBody ProductDto productDto){
        Product product = productService.createNewProduct(productDto);
        return productConverter.entityToDto(product);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
