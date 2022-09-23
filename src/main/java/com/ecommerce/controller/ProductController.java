package com.ecommerce.controller;

;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.entity.ImageModel;
import com.ecommerce.entity.Product;
import com.ecommerce.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@PostMapping(value={"/addProduct"}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public Product addProdcut(@RequestPart("product") Product product,
			@RequestPart("imageFile") MultipartFile[] file) {
		
		Set<ImageModel> imageModels= uploadImage(file);
		product.setProductImages(imageModels);
		return productService.addProduct(product);
	}

	public Set<ImageModel> uploadImage(MultipartFile[] multipartFiles) {
		Set<ImageModel> imageModels = new HashSet<>();
		 for(MultipartFile file: multipartFiles) {
			 ImageModel imageModel = new ImageModel(
			 file.getOriginalFilename(),
			 file.getContentType(),
			 file.getBytes()
		 );
			 imageModels.add(imageModel);
		 }
		 
		 return imageModels;
	}
}
