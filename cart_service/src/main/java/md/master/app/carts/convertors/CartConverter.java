package md.master.app.carts.convertors;

import lombok.RequiredArgsConstructor;
import md.master.app.api.CartDto;
import md.master.app.carts.model.Cart;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CartConverter {
private final CartItemConvertor cartItemConvertor;
    public CartDto entityToDto(Cart cart){
        CartDto cartDto = new CartDto();
        cartDto.setItems(cart.getItems().stream().map(cartItemConvertor::entityToDto).collect(Collectors.toList()));
        cartDto.setTotalPrice(cart.getTotalPrice());
        return cartDto;
    }
}
