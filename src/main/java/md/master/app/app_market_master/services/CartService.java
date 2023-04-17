package md.master.app.app_market_master.services;

import lombok.RequiredArgsConstructor;
import md.master.app.app_market_master.entities.Product;
import md.master.app.app_market_master.repositories.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ProductService productService;

    public List<Product> findAll(){
       return cartRepository.findAll();
    }

    public void addProduct(Long  id){
        Product product = productService.findById(id).orElseThrow(()-> new RuntimeException("Product not found !"));
        cartRepository.addProduct(product);
    }
}
