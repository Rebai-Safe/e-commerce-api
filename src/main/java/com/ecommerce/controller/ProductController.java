package com.ecommerce.controller;


import com.ecommerce.entity.ImageModel;
import com.ecommerce.entity.Product;
import com.ecommerce.model.ApiResponse;
import com.ecommerce.service.ProductService;
import com.ecommerce.util.ApiResponseHandlerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
@RestController
public class ProductController {
	

	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value={"/addProduct"}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<ApiResponse> addProduct(@RequestPart("product") Product product,
												  @RequestPart("imageFile") MultipartFile[] file) throws IOException {

		Set<ImageModel> imageModels= uploadImage(file);
		product.setProductImages(imageModels);

		return  ApiResponseHandlerUtil.generateResponse("product created successfully",
				HttpStatus.CREATED,
				productService.addProduct(product));
	}
	@GetMapping({"/getProducts"})
	public ResponseEntity<ApiResponse> getProducts(@RequestParam(defaultValue = "0") int pageNumber,
												   @RequestParam(defaultValue = "") String searchKeyword){
		return  ApiResponseHandlerUtil.generateResponse("products returned successfully",
				HttpStatus.OK,
				productService.getProducts(pageNumber, searchKeyword));

	}

	@GetMapping({"/getProductById/{productId}"})
	public ResponseEntity<ApiResponse> getProductById(@PathVariable("productId") Integer productId){
		return  ApiResponseHandlerUtil.generateResponse("product returned successfully",
				HttpStatus.OK,
				productService.getProductById(productId));

	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping({"/deleteProduct/{id}"})
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
