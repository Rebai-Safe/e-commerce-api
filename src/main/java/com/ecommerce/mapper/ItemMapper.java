package com.ecommerce.mapper;

import com.ecommerce.dto.ItemDto;
import com.ecommerce.entity.CartItem;
import com.ecommerce.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemMapper {

    @Autowired
    ProductMapper productMapper;

    public ItemDto cartItemToItemDto(CartItem cartItem) {
        ItemDto itemDto = new ItemDto();
        itemDto.setId(cartItem.getId());
        itemDto.setQuantity(cartItem.getQuantity());
        itemDto.setProduct(productMapper.productToProductDto(cartItem.getProduct()));

        return itemDto;
    }

    public ItemDto orderItemToItemDto(OrderItem orderItem) {
        ItemDto itemDto = new ItemDto();
        itemDto.setId(orderItem.getId());
        itemDto.setQuantity(orderItem.getQuantity());
        itemDto.setProduct(productMapper.productToProductDto(orderItem.getProduct()));

        return itemDto;
    }
}
