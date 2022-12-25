package market.api;

import java.util.List;

public class OrderDto {
    private Long id;
    private int totalPrice;
    private List<OrderItemDto> items;

    public OrderDto(Long id, int totalPrice, List<OrderItemDto> items) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.items = items;
    }
}
