package market.carts.integration;

import lombok.RequiredArgsConstructor;
import market.api.ProductDto;
import market.api.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {
    private final WebClient productServiceWebClient;
    @Value("${end-points.product-service.end-point-url}")
    private String msEndPoint;

    public ProductDto getProductById(Long id) {
        return  productServiceWebClient.get()
                .uri(msEndPoint + id)
                .retrieve()
                .onStatus(
                        httpStatus -> httpStatus.value() == HttpStatus.NOT_FOUND.value(),
                        clientResponse -> Mono.error(new ResourceNotFoundException("Товар не найден в продуктовом МС"))
                )
                .bodyToMono(ProductDto.class)
                .block();

    }
}
