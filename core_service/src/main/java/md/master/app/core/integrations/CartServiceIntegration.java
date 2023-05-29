package md.master.app.core.integrations;

import lombok.RequiredArgsConstructor;
import md.master.app.api.CartDto;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class CartServiceIntegration {
    private final WebClient cartServiceWebClient;

    public CartDto getCurrentCart(String username){
        return cartServiceWebClient.get()
                .uri("/api/v1/cart/0" )
                .header("username", username)
                .retrieve()
//                .onStatus(
//                        httpStatus -> httpStatus.value() == HttpStatus.NOT_FOUND.value(),
//                        clientResponse -> Mono.error(new ResourceNotFoundException("Cart not found in carts MS"))
//                )
                .bodyToMono(CartDto.class)
                .block();

    }

    public void clearCurrentCart(String username){
        cartServiceWebClient.get()
                .uri("/api/v1/cart/0/clear" )
                .header("username", username)
                .retrieve()
                .toBodilessEntity()
                .block();

    }

}
