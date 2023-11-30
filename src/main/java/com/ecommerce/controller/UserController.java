package com.ecommerce.controller;

import com.ecommerce.model.ApiResponse;
import com.ecommerce.util.ApiResponseHandlerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.entity.User;
import com.ecommerce.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//	@PostConstruct
	//	public void initRolesAndUsers() {
	//		userService.initRolesAndUser();
	//	}

	@GetMapping({"/forAdmin"})
	@PreAuthorize("hasRole('ADMIN')")
	public String forAdmin() {
		return "this url is only accessible to admin";
	}
	
	@GetMapping({"/forUser"})
	@PreAuthorize("hasRole('USER')")
	public String forUser() {
		return "this url is only accessible to the user";
	}

	@PostMapping({"/registerUser"})
	public ResponseEntity<ApiResponse> registerUser(@RequestBody User user) {
		return  ApiResponseHandlerUtil.generateResponse("product created successfully",
				HttpStatus.CREATED,
				userService.registerUser(user));

	}
}
