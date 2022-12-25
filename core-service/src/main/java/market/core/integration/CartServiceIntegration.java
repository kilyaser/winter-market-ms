package market.core.integration;

import lombok.RequiredArgsConstructor;
import market.api.CartDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CartServiceIntegration {
    private final RestTemplate restTemplate;
    @Value("${cart-service.url}")
    private String url;

    public Optional<CartDto> getCurrentCart() {
        return Optional.ofNullable(restTemplate.getForObject(url, CartDto.class));
    }

    public void clear() {
        restTemplate.getForObject( url + "/clear", CartDto.class);
    }
}
