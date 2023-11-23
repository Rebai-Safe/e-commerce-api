package com.ecommerce.service;

import com.ecommerce.configuration.JwtRequestFilter;
import com.ecommerce.dao.CartDao;
import com.ecommerce.dao.OrderDao;
import com.ecommerce.dao.ProductDao;
import com.ecommerce.dao.UserDao;
import com.ecommerce.entity.*;
import com.ecommerce.enums.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    public void placeOrder(OrderPlaceRequest orderPlaceRequest,
                           boolean isCartCheckout){
        List<OrderProductQuantity> orderProductQuantityList = orderPlaceRequest.getOrderProductQuantityList();
        String currentUser = JwtRequestFilter.CURRENT_USER;
        Users user = userDao.findById(currentUser).get();

        for(OrderProductQuantity orderProductQuantity: orderProductQuantityList){
            Product product = productDao.findById(orderProductQuantity.getProductId()).get();

            Order order = new Order(
                 orderPlaceRequest.getFullName(),
                 orderPlaceRequest.getFullAddress(),
                 orderPlaceRequest.getContactNumber(),
                 orderPlaceRequest.getAltContactNumber(),
                    OrderStatus.PLACED.name(),
                    product.getProductDiscountedPrice() * orderProductQuantity.getQuantity(),
                    product,
                    user
            );
            orderDao.save(order);
        }
        //empty the cart
        if(isCartCheckout){
            List<Cart> carts = cartDao.findByUser(user);
            carts.stream().forEach(c -> cartDao.delete(c));
        }
    }

    public List<Order> getOrders(){
        String currentUser = JwtRequestFilter.CURRENT_USER;
        Users user = userDao.findById(currentUser).get();

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
