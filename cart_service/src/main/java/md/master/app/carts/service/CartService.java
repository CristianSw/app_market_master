package md.master.app.carts.service;

import lombok.RequiredArgsConstructor;

import md.master.app.api.ProductDto;
import md.master.app.carts.integrations.ProductServiceIntegration;
import md.master.app.carts.model.Cart;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductServiceIntegration productServiceIntegration;
    private final RedisTemplate<String, Object> redisTemplate;

    @Value("${cart-service.cart-prefix}")
    private String cartsPrefix;

    public Cart getCurrentCart(String uuid) {
        String targetUuid = cartsPrefix + uuid;
        if (!redisTemplate.hasKey(targetUuid)) {
            redisTemplate.opsForValue().set(targetUuid, new Cart());
        }
        return (Cart) redisTemplate.opsForValue().get(targetUuid);
    }

    public void add(String uuid, Long productId) {
        ProductDto product = productServiceIntegration.getProductById(productId);
        execute(uuid, cart -> cart.add(product));
    }

    public void clear(String uuid) {
        execute(uuid, Cart::clear);
    }

    public void remove(String uuid, Long productId) {
        execute(uuid, cart -> cart.remove(productId));
    }

    public void increaseQuantity(String uuid, Long productId) {
        ProductDto product = productServiceIntegration.getProductById(productId);
        execute(uuid, cart -> cart.increaseQuantity(productId));
    }

    public void decreaseQuantity(String uuid, Long productId) {
        ProductDto product = productServiceIntegration.getProductById(productId);
        execute(uuid, cart -> cart.decreaseQuantity(productId));
    }

    private void execute(String uuid, Consumer<Cart> operation) {
        Cart cart = getCurrentCart(uuid);
        operation.accept(cart);
        redisTemplate.opsForValue().set(cartsPrefix + uuid, cart);
    }
}
