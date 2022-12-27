package market.core.servicies;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import market.api.CartDto;
import market.core.converters.OrderItemConverter;
import market.core.integration.CartServiceIntegration;
import market.core.repositories.OrderRepository;
import market.core.entities.Order;
import market.core.entities.OrderItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final CartServiceIntegration cartServiceIntegration;
    private final OrderItemConverter orderItemConverter;
    private final OrderRepository orderRepository;
    @Transactional
    public void creatOrder(String username) {
        CartDto cart = cartServiceIntegration.getCurrentCart();
        Order order = new Order();
        order.setUsername(username);
        order.setTotalPrice(cart.getTotalPrice());
        List<OrderItem> items = cart.getItems().stream().map(oi -> orderItemConverter.cartItemToOrderItem(oi, order)).toList();
        order.setItems(items);
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());
        orderRepository.save(order);
        cartServiceIntegration.clear();

    }
}
