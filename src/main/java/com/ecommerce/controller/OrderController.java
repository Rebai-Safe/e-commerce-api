package com.ecommerce.controller;

import com.ecommerce.entity.Order;
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
    @PostMapping("/placeOrder")
    public Order placeOrder(@RequestBody Order order){
            return orderService.placeOrder(order);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping({"/getOrder/{orderId}"})
    public Order getOrderById(@PathVariable Integer orderId){
        return this.orderService.getOrderById(orderId);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping({"/getUserOrders"})
    public List<Order> getUserOrders(){
         return this.orderService.getUserOrders();
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
