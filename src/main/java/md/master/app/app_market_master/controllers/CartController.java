package md.master.app.app_market_master.controllers;

import lombok.RequiredArgsConstructor;

import md.master.app.app_market_master.dtos.Cart;
import md.master.app.app_market_master.services.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {
    private final CartService cartService;

    @GetMapping
    public Cart getCurrentCart(){
        return cartService.getCurrentCart();
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

    @DeleteMapping
    public void clearCart(){
        cartService.clearCart();
    }
}