package com.ecommerce.dao;

import com.ecommerce.entity.Cart;
import com.ecommerce.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface CartDao extends JpaRepository<Cart, Integer> {
    List<Cart> findByUser(Users user);
}
