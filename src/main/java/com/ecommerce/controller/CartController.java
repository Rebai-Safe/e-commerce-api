package com.ecommerce.controller;

import com.ecommerce.entity.Cart;
import com.ecommerce.model.ApiResponse;
import com.ecommerce.service.CartService;
import com.ecommerce.util.ApiResponseHandlerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartController {


    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping({"/addToCart/{productId}/{quantity}"})
    public ResponseEntity<ApiResponse> addToCart(@PathVariable Integer productId, @PathVariable Integer quantity){
        return  ApiResponseHandlerUtil.generateResponse("item added successfully",
                HttpStatus.CREATED,
                cartService.addToCart(productId, quantity));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping({"/getCart"})
    public ResponseEntity<ApiResponse> getCart(){
        return  ApiResponseHandlerUtil.generateResponse("cart returned successfully",
                HttpStatus.OK,
                cartService.getCart());

    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping({"/deleteItem/{cartId}/{productId}"})
    public void DeleteCartItem(@PathVariable Integer cartId, @PathVariable Integer productId){
        this.cartService.deleteCartItem(cartId, productId);
    }
}
