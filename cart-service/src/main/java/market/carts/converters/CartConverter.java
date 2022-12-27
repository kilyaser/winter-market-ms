package market.carts.converters;

import lombok.RequiredArgsConstructor;
import market.api.CartDto;
import market.carts.models.Cart;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CartConverter {
    private final CartItemConverter cartItemConverter;

    public CartDto entityToDto(Cart cart) {
        CartDto cartDto = new CartDto();
        cartDto.setTotalPrice(cart.getTotalPrice());
        cartDto.setItems(cart.getItems().stream().map(cartItemConverter::entityToDto).toList());
        return cartDto;
    }

}
