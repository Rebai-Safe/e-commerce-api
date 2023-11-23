package com.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.ecommerce.configuration.JwtRequestFilter;
import com.ecommerce.dao.CartDao;
import com.ecommerce.dao.UserDao;
import com.ecommerce.entity.Cart;
import com.ecommerce.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ecommerce.dao.ProductDao;
import com.ecommerce.entity.Product;

@Service
public class ProductService {

	@Autowired
	ProductDao productDao;

	@Autowired
	UserDao userDao;

	@Autowired
	CartDao cartDao;
	
	public Product addProduct(Product product) {
		return productDao.save(product);
	}

	public Product getProductById(Integer productId){
		return productDao.findById(productId).get();
	}
	public List<Product> getProducts(int pageNumber, String searchKeyword){
		Pageable pageable = PageRequest.of(pageNumber, 4);
		if(searchKeyword.isEmpty()){
			return productDao.findAll(pageable);
		}else{
			return productDao.findByProductNameContainingIgnoreCaseOrProductDescriptionContainingIgnoreCase(searchKeyword, searchKeyword, pageable);
		}

	}
	
	public void deleteProduct(Integer id) {
		productDao.deleteById(id);
	}

    public List<Product> getProductDetails(boolean isSingleProductCheckout, Integer productId) {
		if(isSingleProductCheckout && productId != 0){
			//we are going to buy a single product
			List<Product> list = new ArrayList<>();
			Product product = productDao.findById(productId).get();
			list.add(product);
			return list;
		}else{
			//we are going to checkout entire cart
			String currentUser = JwtRequestFilter.CURRENT_USER;
			Users user = userDao.findById(currentUser).get();
			List<Cart> carts = cartDao.findByUser(user);
			return carts.stream().map(Cart::getProduct).collect(Collectors.toList());
		}
    }
}
