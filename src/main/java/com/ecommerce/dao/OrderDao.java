package com.ecommerce.dao;

import com.ecommerce.entity.Order;
import com.ecommerce.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDao extends JpaRepository<Order, Integer> {
     List<Order> findByUser(Users user);
     List<Order> findByOrderStatus(String status);
}
