package market.core.converters;

import lombok.RequiredArgsConstructor;
import market.api.OrderDto;
import market.api.OrderItemDto;
import market.core.entities.Order;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderConverter {
    private final OrderItemConverter orderItemConverter;

    public OrderDto entityToDto(Order order) {
        List<OrderItemDto> items = order.getItems().stream().map(orderItemConverter::entityToDto).toList();
        return new OrderDto(order.getId(), order.getTotalPrice(), items);
    }
}
