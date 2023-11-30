package com.ecommerce.dto;

import java.util.List;

public class CartDto {
    private Integer cartId;
    private List<ItemDto> cartItems;

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public List<ItemDto> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<ItemDto> cartItems) {
        this.cartItems = cartItems;
    }
}
