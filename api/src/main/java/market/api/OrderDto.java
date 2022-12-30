package market.api;

import java.math.BigDecimal;
import java.util.List;

public class OrderDto {
    private Long id;
    private BigDecimal totalPrice;
    private List<OrderItemDto> items;

    public OrderDto(Long id, BigDecimal totalPrice, List<OrderItemDto> items) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.items = items;
    }
}
