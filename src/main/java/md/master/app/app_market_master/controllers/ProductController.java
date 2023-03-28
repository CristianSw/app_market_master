package md.master.app.app_market_master.controllers;

import lombok.RequiredArgsConstructor;
import md.master.app.app_market_master.entities.Product;
import md.master.app.app_market_master.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @GetMapping
    public List<Product> findAllProducts(){
    return productService.findAll();
}

    @GetMapping("/{id}")
    public Product findProductById(@PathVariable Long id){
        return productService.findById(id).get();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        productService.deleteById(id);
    }
}
