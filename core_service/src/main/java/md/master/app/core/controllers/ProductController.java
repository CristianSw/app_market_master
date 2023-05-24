package md.master.app.core.controllers;

import md.master.app.api.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import md.master.app.api.ProductDto;
import md.master.app.core.convertors.ProductConverter;
import md.master.app.core.entities.Product;
import md.master.app.core.services.ProductService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
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
    public List<ProductDto> findProducts(
            @RequestParam(required = false, name = "min_price") Integer min_price,
            @RequestParam(required = false, name = "max_price") Integer max_price,
            @RequestParam(required = false, name = "title") String title,
            @RequestParam(defaultValue = "1", name = "p") Integer page
    ) {
        if (page < 1){
            page = 1;
        }
        Specification<Product> spec = productService.createSpecByFilters(min_price,max_price,title);

        return productService.findAll(spec,page- 1).map(productConverter::entityToDto).getContent();
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
