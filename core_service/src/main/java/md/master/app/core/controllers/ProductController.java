package md.master.app.core.controllers;

import lombok.RequiredArgsConstructor;
import md.master.app.api.ProductDto;
import md.master.app.core.convertors.ProductConverter;
import md.master.app.core.exceptions.ResourceNotFoundException;
import md.master.app.core.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProductController {
    private final ProductService productService;
    private final ProductConverter productConverter;


    @GetMapping
    public List<ProductDto> findAllProducts() {
        return productService.findAll().stream().map(productConverter::entityToDto ).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductDto findProductById(@PathVariable Long id) {
        return productConverter.entityToDto(productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found,id =" + id)));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto createNewProducts(@RequestBody ProductDto productDto){
        return productConverter.entityToDto(productService.createNewProduct(productDto));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
