package md.master.app.core.integrations;

import lombok.RequiredArgsConstructor;
import md.master.app.api.CartDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CartServiceIntegration {
    private final RestTemplate restTemplate;
    public Optional<CartDto> getCurrentCart(){
        return Optional.ofNullable(restTemplate.getForObject("http://localhost:8190/market-carts/api/v1/cart", CartDto.class));
    }
    public void clearCurrentCart(){
        restTemplate.getForObject("http://localhost:8190/market-carts/api/v1/cart/clear/", Void.class);
    }
}
