package com.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.dao.ProductDao;
import com.ecommerce.entity.Product;

@Service
public class ProductService {

	@Autowired
	ProductDao productDao;
	
	public Product addProduct(Product product) {
		return productDao.save(product);
	}
}
