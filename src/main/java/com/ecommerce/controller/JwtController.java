package com.ecommerce.controller;

import com.ecommerce.model.ApiResponse;
import com.ecommerce.util.ApiResponseHandlerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.model.JwtRequest;
import com.ecommerce.model.JwtResponse;
import com.ecommerce.service.JwtService;

@RestController
@CrossOrigin
public class JwtController {


	private final JwtService jwtService;
	public JwtController(JwtService jwtService) {
		this.jwtService = jwtService;
	}

	@PostMapping(value = "/authenticate", produces = "application/json")
	public ResponseEntity<ApiResponse> createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception{
		return  ApiResponseHandlerUtil.generateResponse("user authenticated successfully",
				HttpStatus.OK,
				jwtService.createJwtToken(jwtRequest));
	}
}
