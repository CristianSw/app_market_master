package md.master.app.app_market_master.controllers;

import lombok.RequiredArgsConstructor;

import md.master.app.app_market_master.entities.Product;
import md.master.app.app_market_master.services.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    @GetMapping
    public List<Product> findAllProducts(){
        return cartService.findAll();
    }

    @GetMapping("/{id}")
    public void addProductToCart(@PathVariable long id){
        cartService.addProduct(id);
    }
}