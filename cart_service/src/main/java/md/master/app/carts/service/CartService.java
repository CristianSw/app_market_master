package md.master.app.carts.service;

import lombok.RequiredArgsConstructor;

import md.master.app.api.ProductDto;
import md.master.app.api.ResourceNotFoundException;
import md.master.app.carts.integrations.ProductServiceIntegration;
import md.master.app.carts.model.Cart;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductServiceIntegration productServiceIntegration;
    private Cart tempCart;

    @PostConstruct
    public void init() {
        tempCart = new Cart();
    }

    public Cart getCurrentCart() {
        return tempCart;
    }

    public void add(Long productId) {
        ProductDto product = productServiceIntegration.getProductById(productId);
        tempCart.add(product);
    }

    public void clear() {
        tempCart.clear();
    }

    public void remove(Long productId){
        tempCart.remove(productId);
    }

    public void increaseQuantity(Long productId) {
        ProductDto product = productServiceIntegration.getProductById(productId);
        tempCart.increaseQuantity(productId);
    }

    public void decreaseQuantity(Long productId) {
        ProductDto product = productServiceIntegration.getProductById(productId);
        tempCart.decreaseQuantity(productId);
    }
}
