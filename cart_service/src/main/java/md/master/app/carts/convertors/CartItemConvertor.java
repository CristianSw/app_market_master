package md.master.app.carts.convertors;

import md.master.app.api.CartItemDto;
import md.master.app.carts.model.CartItem;
import org.springframework.stereotype.Component;

@Component
public class CartItemConvertor {

    public CartItemDto entityToDto(CartItem cartItem){
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setProductId(cartItemDto.getProductId());
        cartItemDto.setProductTitle(cartItemDto.getProductTitle());
        cartItemDto.setQuantity(cartItemDto.getQuantity());
        cartItemDto.setPrice(cartItem.getPrice());
        cartItemDto.setPricePerProduct(cartItemDto.getPricePerProduct());
        return cartItemDto;
    }
}
