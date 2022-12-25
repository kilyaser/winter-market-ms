package market.core.converters;

import lombok.RequiredArgsConstructor;
import market.api.CartItemDto;
import market.api.OrderItemDto;
import market.api.ResourceNotFoundException;
import market.core.entities.Order;
import market.core.entities.OrderItem;
import market.core.servicies.ProductService;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class OrderItemConverter {
    private final ProductService productService;

    public OrderItem cartItemToOrderItem(CartItemDto cartItem, Order order) {
        var product = productService.findById(cartItem.getProductId()).orElseThrow(() -> new ResourceNotFoundException(String.format("Product id: %s not found", cartItem.getProductId())));
        return new OrderItem(null,
                product,
                order,
                cartItem.getQuantity(),
                cartItem.getPricePerProduct(),
                cartItem.getPrice(),
                LocalDateTime.now(),
                LocalDateTime.now());
    }

    public OrderItemDto entityToDto(OrderItem o) {
        return new OrderItemDto(o.getId(), o.getQuantity(), o.getPricePerProduct(), o.getPrice());
    }
}
