package com.ecommerce.mapper;

import com.ecommerce.dto.CartDto;
import com.ecommerce.dto.ItemDto;
import com.ecommerce.entity.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartMapper {

    @Autowired
    ItemMapper itemMapper;
    public CartDto cartToCartDto(Cart cart){
        CartDto cartDto = new CartDto();
         cartDto.setCartId(cart.getCartId());
         List<ItemDto> cartItemsDto = new ArrayList<>();
         cart.getCartItems().forEach(cartItem -> {
             cartItemsDto.add(itemMapper.cartItemToItemDto(cartItem));
         });
         cartDto.setCartItems(cartItemsDto);

         return cartDto;
    }
}
