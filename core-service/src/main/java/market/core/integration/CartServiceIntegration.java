package market.core.integration;

import lombok.RequiredArgsConstructor;
import market.api.CartDto;
import market.api.ProductDto;
import market.api.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CartServiceIntegration {
    private final WebClient cartServiceWebClient;
    @Value("${end-points.product-service.end-point-url}")
    private String msEndPoint;

    public CartDto getCurrentCart() {
        return  cartServiceWebClient.get()
                .uri(msEndPoint)
                .retrieve()
                .onStatus(
                        httpStatus -> httpStatus.value() == HttpStatus.NOT_FOUND.value(),
                        clientResponse -> Mono.error(new ResourceNotFoundException("Cart wasn't found id cart-service"))
                )
                .bodyToMono(CartDto.class)
                .block();
    }
    public void clear() {
        cartServiceWebClient.get()
                .uri(msEndPoint + "/clear")
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}

