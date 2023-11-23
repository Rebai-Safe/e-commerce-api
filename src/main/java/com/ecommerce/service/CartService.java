package com.ecommerce.service;

import com.ecommerce.configuration.JwtRequestFilter;
import com.ecommerce.dao.CartDao;
import com.ecommerce.dao.ProductDao;
import com.ecommerce.dao.UserDao;
import com.ecommerce.entity.Cart;
import com.ecommerce.entity.Product;
import com.ecommerce.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CartService {

    @Autowired
    private CartDao cartDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;

    public Cart addToCart(Integer productId) {
        Product product = productDao.findById(productId).get();
        String currentUser = JwtRequestFilter.CURRENT_USER;

        if (currentUser != null) {
            Users user = userDao.findById(currentUser).get();
            List<Cart> carts = cartDao.findByUser(user);
            List<Cart> filteredList = carts.stream().filter(c -> Objects.equals(c.getProduct().getProductId(), productId)).collect(Collectors.toList());
            if(filteredList.size() > 0)
                 return null;
            if (user != null & product != null) {
                Cart cart = new Cart(product, user);
                return cartDao.save(cart);
            }
        }
        return null;
    }

    public List<Cart> getCarts(){
        String currentUser = JwtRequestFilter.CURRENT_USER;
        Users user = userDao.findById(currentUser).get();
        return cartDao.findByUser(user);
    }

    public void deleteCartItem(Integer cartId){
        cartDao.deleteById(cartId);
    }
}
