package md.master.app.carts.service;

import lombok.RequiredArgsConstructor;

import md.master.app.api.ProductDto;
import md.master.app.carts.integrations.ProductServiceIntegration;
import md.master.app.carts.model.Cart;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductServiceIntegration productServiceIntegration;
    @Value("${cart-service.cart-prefix}")
    private String cartsPrefix;
    private Map<String,Cart> carts;

    @PostConstruct
    public void init() {
        carts = new HashMap<>();
    }

    public Cart getCurrentCart(String uuid) {
        String targetUuid = cartsPrefix + uuid;
        if (!carts.containsKey(targetUuid)){
            carts.put(uuid, new Cart());
        }
        return carts.get(uuid);
    }

    public void add(String uuid, Long productId) {
        ProductDto product = productServiceIntegration.getProductById(productId);
        getCurrentCart(uuid).add(product);
    }

    public void clear(String uuid) {
        getCurrentCart(uuid).clear();
    }

    public void remove(String uuid, Long productId){
        getCurrentCart(uuid).remove(productId);
    }

    public void increaseQuantity(String uuid, Long productId) {
        ProductDto product = productServiceIntegration.getProductById(productId);
        getCurrentCart(uuid).increaseQuantity(productId);
    }

    public void decreaseQuantity(String uuid, Long productId) {
        ProductDto product = productServiceIntegration.getProductById(productId);
        getCurrentCart(uuid).decreaseQuantity(productId);
    }
}
