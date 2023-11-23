package com.ecommerce.controller;

import com.ecommerce.entity.Order;
import com.ecommerce.entity.OrderPlaceRequest;
import com.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/place-order/{isCartCheckout}")
    public void placeOrder(@PathVariable boolean isCartCheckout,
                           @RequestBody OrderPlaceRequest orderPlaceRequest){
            orderService.placeOrder(orderPlaceRequest, isCartCheckout);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping({"/getOrders"})
    public List<Order> getOrders(){
         return this.orderService.getOrders();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping({"/getAllOrders/{status}"})
    public List<Order> getAllOrders(@PathVariable String status){
        return this.orderService.getAllOrders(status);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping({"/markOrderDelivered/{orderId}"})
    public void markOrderDelivered(@PathVariable Integer orderId){
        orderService.markOrderDelivered(orderId);
    }
}
