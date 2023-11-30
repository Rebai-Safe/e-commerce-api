package com.ecommerce.dao;

import com.ecommerce.entity.Cart;
import com.ecommerce.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemDao extends JpaRepository<CartItem, Long> {
    List<CartItem> findByCart(Cart cart);
    void deleteByCartAndAndProduct_ProductId(Cart cart, Integer ProductId);
}
