package com.ecommerce.service;

import com.ecommerce.configuration.JwtRequestFilter;
import com.ecommerce.dao.CartDao;
import com.ecommerce.dao.OrderDao;
import com.ecommerce.dao.ProductDao;
import com.ecommerce.dao.UserDao;
import com.ecommerce.dto.OrderDto;
import com.ecommerce.entity.*;
import com.ecommerce.enums.OrderStatus;
import com.ecommerce.mapper.OrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {



    private final OrderDao orderDao;
    private final UserDao userDao;
    private final CartDao cartDao;
    private final OrderMapper orderMapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

    public OrderService(OrderDao orderDao, UserDao userDao, CartDao cartDao, OrderMapper orderMapper) {
        this.orderDao = orderDao;
        this.userDao = userDao;
        this.cartDao = cartDao;
        this.orderMapper = orderMapper;
    }

    @Transactional
    public OrderDto placeOrder(Order order) {
        String currentUser = JwtRequestFilter.CURRENT_USER;
        User user = userDao.findById(currentUser).get();
        order.setUser(user);
        order.setOrderStatus(OrderStatus.PLACED.name());

        //setting order id for order items
        order.getOrderItems().forEach(orderItem -> orderItem.setOrder(order));
        LOGGER.info("Placing order: " + order);

        //empty the cart
        Cart currentUserCart = cartDao.findByUser(user);
        cartDao.delete(currentUserCart);

        return orderMapper.orderToOrderDto(orderDao.save(order));
    }

    public OrderDto getOrderById(Integer orderId) {
        return orderMapper.orderToOrderDto(orderDao.findById(orderId).get());
    }

    public List<OrderDto> getUserOrders() {
        String currentUser = JwtRequestFilter.CURRENT_USER;
        User user = userDao.findById(currentUser).get();

        List<OrderDto> orderDtos = new ArrayList<>();
        List<Order> orders = orderDao.findByUser(user);

        orders.forEach(order -> {
            orderDtos.add(orderMapper.orderToOrderDto(order));
        });
        return orderDtos;
    }

    public List<OrderDto> getAllOrders(String status) {
        List<OrderDto> orderDtos = new ArrayList<>();
        List<Order> orders;
        if (status.equals(OrderStatus.ALL.name()))

            orders = orderDao.findAll();
        else {
            orders = orderDao.findByOrderStatus(status);
        }

        orders.forEach(order -> {
            orderDtos.add(orderMapper.orderToOrderDto(order));
        });
        return orderDtos;
    }

    public void markOrderDelivered(Integer orderId) {
        Order order = orderDao.findById(orderId).get();
        order.setOrderStatus(OrderStatus.DELIVERED.name());
        orderDao.save(order);
    }
}
