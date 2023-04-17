package md.master.app.app_market_master.services;

import lombok.RequiredArgsConstructor;
import md.master.app.app_market_master.dtos.Cart;

import md.master.app.app_market_master.entities.Product;
import md.master.app.app_market_master.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductService productService;

    private Cart tempCart;

    @PostConstruct
    public void init() {
        tempCart = new Cart();
    }

    public Cart getCurrentCart(){
        return tempCart;
    }

    public void add(Long productId){
    Product product = productService.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Unable to add product ith id: " + productId + ", product not found"));
    tempCart.add(product);
    }
}
