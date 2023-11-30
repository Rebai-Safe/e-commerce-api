package com.ecommerce.dao;

import com.ecommerce.entity.Order;
import com.ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDao extends JpaRepository<Order, Integer> {
     List<Order> findByUser(User user);
     List<Order> findByOrderStatus(String status);
}
