package market.carts.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import market.api.CartDto;
import market.carts.converters.CartConverter;
import market.carts.servicies.CartService;
import org.springframework.web.bind.annotation.*;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
@Slf4j
public class CartController {
    private final CartService cartService;
    private final CartConverter cartConverter;
    @GetMapping("/add/{id}")
    public void addToCart(@PathVariable Long id) {
        log.info("get method /api/v1/cart/add/{}", id);
        cartService.add(id);
    }
    @GetMapping
    public CartDto getCurrentCart() {
        return cartConverter.entityToDto(cartService.getCurrentCart());
    }
    @GetMapping("/delete/{id}")
    public void deleteProductFromCart(@PathVariable Long id) {
        cartService.deleteProductFromCart(id);
    }

    @GetMapping("/deleteQuantity/{id}")
    public void deleteAllQuantity(@PathVariable Long id) {
        cartService.deleteAllQuantity(id);
    }

    @GetMapping("/clear")
    public void clearCart() {
        cartService.clear();
    }

}
