package com.ecommerce.controller;

import com.ecommerce.entity.Cart;
import com.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping({"/addToCart/{productId}/{quantity}"})
    public Cart addToCart(@PathVariable Integer productId,@PathVariable Integer quantity){
        return cartService.addToCart(productId, quantity);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping({"/getCart"})
    public Cart getCart(){
        return cartService.getCart();
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping({"/deleteItem/{cartId}/{productId}"})
    public void DeleteCartItem(@PathVariable Integer cartId, @PathVariable Integer productId){
        this.cartService.deleteCartItem(cartId, productId);
    }
}
