package com.ecommerce.mapper;

import com.ecommerce.dto.ItemDto;
import com.ecommerce.dto.OrderDto;
import com.ecommerce.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderMapper {

    @Autowired
    ItemMapper itemMapper;

    public OrderDto orderToOrderDto(Order order){
        OrderDto orderDto = new OrderDto();
         orderDto.setOrderId(order.getOrderId());
         orderDto.setOrderAmount(order.getOrderAmount());
         orderDto.setOrderStatus(order.getOrderStatus());

        List<ItemDto> orderItemsDto = new ArrayList<>();
        order.getOrderItems().forEach(orderItem -> {
            orderItemsDto.add(itemMapper.orderItemToItemDto(orderItem));
        });
        orderDto.setOrderItems(orderItemsDto);

        return orderDto;
    }
}
