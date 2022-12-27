package market.core.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import market.api.OrderDto;
import market.core.converters.OrderConverter;
import market.core.repositories.OrderRepository;
import market.core.servicies.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final OrderService orderService;
    private final OrderRepository orderRepository;
    private final OrderConverter orderConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void creatOrder(@RequestHeader String username) {
        orderService.creatOrder(username);
    }

    @GetMapping
    public List<OrderDto> getOrders(Principal principal) {
        log.info("getOrders method was required");
        return orderRepository.findAll().stream().map(orderConverter::entityToDto).toList();

    }

}
