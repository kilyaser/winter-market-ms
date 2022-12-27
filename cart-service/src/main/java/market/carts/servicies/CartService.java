package market.carts.servicies;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import market.api.ProductDto;
import market.api.ResourceNotFoundException;
import market.carts.integration.ProductServiceIntegration;
import market.carts.models.Cart;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class CartService {
    private final ProductServiceIntegration productServiceIntegration;
    private Cart tempCart;

    public void add(Long id) {
        ProductDto product = productServiceIntegration.getProductById(id);
        tempCart.add(product);
        log.info("tempCart size after adding " + tempCart.getItems().size());
    }

    public void deleteProductFromCart(Long id) {
        tempCart.remove(id);
    }
    public void deleteAllQuantity(Long id) {
        tempCart.removeAllQuantity(id);
    }
    public Cart getCurrentCart() {
        return tempCart;
    }
    public void clear() {
        log.info("clear cart in cartService");
        tempCart.clear();
    }

    @PostConstruct
    public void init() {
        tempCart = new Cart();
    }
}
