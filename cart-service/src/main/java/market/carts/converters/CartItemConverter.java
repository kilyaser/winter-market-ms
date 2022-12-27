package market.carts.converters;

import market.api.CartItemDto;
import market.carts.models.CartItem;
import org.springframework.stereotype.Component;

@Component
public class CartItemConverter {

    public CartItemDto entityToDto(CartItem cartItem) {
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setProductId(cartItem.getProductId());
        cartItemDto.setProductTitle(cartItem.getProductTitle());
        cartItemDto.setQuantity(cartItem.getQuantity());
        cartItemDto.setPricePerProduct(cartItem.getPricePerProduct());
        cartItemDto.setPrice(cartItem.getPrice());
        return cartItemDto;
    }
}
