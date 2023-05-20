package md.master.app.carts.controllers;

import lombok.RequiredArgsConstructor;


import md.master.app.api.CartDto;
import md.master.app.carts.convertors.CartConverter;
import md.master.app.carts.service.CartService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {
    private final CartService cartService;
    private final CartConverter cartConverter;

    @GetMapping
    public CartDto getCurrentCart(){
        return cartConverter.entityToDto(cartService.getCurrentCart());
    }

    @GetMapping("/add/{id}")
    public void addProductToCart(@PathVariable Long id){
        cartService.add(id);
    }

    @GetMapping("/inc/{id}")
    public void increaseQuantity(@PathVariable Long id){
        cartService.increaseQuantity(id);
    }
    @GetMapping("/dec/{id}")
    public void decreaseQuantity(@PathVariable Long id){
        cartService.decreaseQuantity(id);
    }

    @GetMapping("/clear")
    public void clear(){
        cartService.clear();
    }
    @GetMapping("/remove/{id}")
    public void removeFromCart(@PathVariable Long id){
        cartService.remove(id);
    }
}