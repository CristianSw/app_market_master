package md.master.app.carts.integrations;

import lombok.RequiredArgsConstructor;
import md.master.app.api.ProductDto;
import md.master.app.api.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.http.HttpClient;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {
    private final WebClient productServiceWebClient;

    public ProductDto getProductById(Long id){
        return productServiceWebClient.get()
                .uri("api/v1/products/" + id)
                .retrieve()
                .onStatus(
                        httpStatus -> httpStatus.value() == HttpStatus.NOT_FOUND.value(),
                        clientResponse -> Mono.error(new ResourceNotFoundException("Product not found in products MS"))
                )
                .bodyToMono(ProductDto.class)
                .block();

    }

}
