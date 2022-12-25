package market.carts.integration;

import lombok.RequiredArgsConstructor;
import market.api.ProductDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {
    @Value("${core-service.url}")
    private String url;
    private final RestTemplate restTemplate;

    public Optional<ProductDto> getProductById(Long id) {
        return Optional.ofNullable(restTemplate.getForObject( url + id, ProductDto.class));
    }
}
