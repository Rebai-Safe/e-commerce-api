package com.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.ecommerce.configuration.JwtRequestFilter;
import com.ecommerce.dao.CartDao;
import com.ecommerce.dao.UserDao;
import com.ecommerce.dto.ProductDto;
import com.ecommerce.entity.Cart;
import com.ecommerce.entity.User;
import com.ecommerce.mapper.ProductMapper;
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

	@Autowired
	ProductMapper productMapper;
	
	public ProductDto addProduct(Product product) {
		return productMapper.productToProductDto(productDao.save(product));
	}

	public ProductDto getProductById(Integer productId){
		return productMapper.productToProductDto(productDao.findById(productId).get());
	}
	public List<ProductDto> getProducts(int pageNumber, String searchKeyword){
		Pageable pageable = PageRequest.of(pageNumber, 4);
		List<ProductDto> productDtos = new ArrayList<>();
		List<Product> products;
		if(searchKeyword.isEmpty()){
			products = productDao.findAll(pageable);
		}else{
			products= productDao.findByProductNameContainingIgnoreCaseOrProductDescriptionContainingIgnoreCase(searchKeyword, searchKeyword, pageable);
		}
		products.forEach(product -> {
			productDtos.add(productMapper.productToProductDto(product));
		});
		return productDtos;
	}
	public void deleteProduct(Integer id) {
		productDao.deleteById(id);
	}

}
