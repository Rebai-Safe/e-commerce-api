package com.ecommerce.service;

import com.ecommerce.configuration.JwtRequestFilter;
import com.ecommerce.dao.CartDao;
import com.ecommerce.dao.OrderDao;
import com.ecommerce.dao.ProductDao;
import com.ecommerce.dao.UserDao;
import com.ecommerce.entity.*;
import com.ecommerce.enums.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {


    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private CartDao cartDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

    @Transactional
    public Order placeOrder(Order order){
        String currentUser = JwtRequestFilter.CURRENT_USER;
        User user = userDao.findById(currentUser).get();
        order.setUser(user);
        order.setOrderStatus(OrderStatus.PLACED.name());

        //setting order id for order items
        order.getOrderItems().forEach(orderItem -> orderItem.setOrder(order));
        LOGGER.info("Placing order: "+order);

        //empty the cart
        Cart currentUserCart = cartDao.findByUser(user);
        cartDao.delete(currentUserCart);

        return orderDao.save(order);
    }

    public Order getOrderById(Integer orderId){
        return orderDao.findById(orderId).get();
    }

    public List<Order> getUserOrders(){
        String currentUser = JwtRequestFilter.CURRENT_USER;
        User user = userDao.findById(currentUser).get();

        return orderDao.findByUser(user);
    }

    public List<Order> getAllOrders(String status){
        if(status.equals(OrderStatus.ALL.name()))
            return orderDao.findAll();
        else{
            return orderDao.findByOrderStatus(status);
        }
    }

    public void markOrderDelivered(Integer orderId){
        Order order = orderDao.findById(orderId).get();
        order.setOrderStatus(OrderStatus.DELIVERED.name());
        orderDao.save(order);
    }
}
