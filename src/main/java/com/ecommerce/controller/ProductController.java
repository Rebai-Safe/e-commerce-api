package com.ecommerce.controller;


import com.ecommerce.entity.ImageModel;
import com.ecommerce.entity.Product;
import com.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@RestController
public class ProductController {
	
	@Autowired
	ProductService productService;

	@GetMapping({"/get-products"})
	public List<Product> getProducts(@RequestParam(defaultValue = "0") int pageNumber,
									 @RequestParam(defaultValue = "") String searchKeyword){
		return productService.getProducts(pageNumber, searchKeyword);
	}

	@GetMapping({"/get-product-byId/{productId}"})
	public Product getProductById(@PathVariable("productId") Integer productId){
		return productService.getProductById(productId);
	}

	@PreAuthorize("hasRole('USER')")
	@GetMapping({"/get-product-details/{productId}/{isSingleProductCheckout}"})
	public List<Product> getProductDetails(@PathVariable("productId") int productId,
									       @PathVariable("isSingleProductCheckout") boolean isSingleProductCheckout){

		return productService.getProductDetails(isSingleProductCheckout, productId);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value={"/add-product"}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public Product addProduct(@RequestPart("product") Product product,
			@RequestPart("imageFile") MultipartFile[] file) throws IOException {
		
		Set<ImageModel> imageModels= uploadImage(file);
		product.setProductImages(imageModels);
		return productService.addProduct(product);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping({"/delete-product/{id}"})
	public void deleteProduct(@PathVariable Integer id) {
		productService.deleteProduct(id);
	}


	private Set<ImageModel> uploadImage(MultipartFile[] multipartFiles) throws IOException {
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
