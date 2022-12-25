package market.core.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import market.api.OrderDto;
import market.api.ResourceNotFoundException;
import market.core.converters.OrderConverter;
import market.core.entities.User;
import market.core.repositories.OrderRepository;
import market.core.servicies.OrderService;
import market.core.servicies.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final UserService userService;
    private final OrderService orderService;
    private final OrderRepository orderRepository;
    private final OrderConverter orderConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void creatOrder(Principal principal) {
        log.info("Сработа orderController user {}", principal.getName());
        User user = userService.findByUsername(principal.getName())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User with username: %s not found", principal.getName())));
        orderService.creatOrder(user);
    }

    @GetMapping
    public List<OrderDto> getOrders(Principal principal) {
        return orderRepository.findAll().stream().map(orderConverter::entityToDto).toList();

    }

}
