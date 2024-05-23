package com.ecommerce.controller;

import com.ecommerce.entity.Order;
import com.ecommerce.model.ApiResponse;
import com.ecommerce.service.OrderService;
import com.ecommerce.util.ApiResponseHandlerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {


    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/placeOrder")
    public ResponseEntity<ApiResponse> placeOrder(@RequestBody Order order){
        return  ApiResponseHandlerUtil.generateResponse("order placed successfully",
                HttpStatus.CREATED,
                orderService.placeOrder(order));

    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping({"/getOrder/{orderId}"})
    public ResponseEntity<ApiResponse> getOrderById(@PathVariable Integer orderId){
        return  ApiResponseHandlerUtil.generateResponse("order returned successfully",
                HttpStatus.OK,
                orderService.getOrderById(orderId));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping({"/getUserOrders"})
    public ResponseEntity<ApiResponse> getUserOrders(){
        return  ApiResponseHandlerUtil.generateResponse("orders returned successfully",
                HttpStatus.OK,
                orderService.getUserOrders());

    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping({"/getAllOrders/{status}"})
    public ResponseEntity<ApiResponse> getAllOrders(@PathVariable String status){
        return  ApiResponseHandlerUtil.generateResponse("orders returned successfully",
                HttpStatus.OK,
                orderService.getAllOrders(status));

    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping({"/markOrderDelivered/{orderId}"})
    public void markOrderDelivered(@PathVariable Integer orderId){
        orderService.markOrderDelivered(orderId);
    }
}
