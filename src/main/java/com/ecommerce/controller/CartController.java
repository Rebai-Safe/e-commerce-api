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
    @GetMapping({"/addToCart/{productId}"})
    public Cart addToCart(@PathVariable Integer productId){
        return cartService.addToCart(productId);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping({"/getCarts"})
    public List<Cart> getCarts(){
        return cartService.getCarts();
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping({"/deleteItem/{cartId}"})
    public void DeleteCartItem(@PathVariable Integer cartId){
        this.cartService.deleteCartItem(cartId);
    }
}
