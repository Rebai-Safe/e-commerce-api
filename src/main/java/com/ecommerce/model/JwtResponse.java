package com.ecommerce.model;


import com.ecommerce.dto.UserDto;


public class JwtResponse {

	private UserDto user;
	private String  jwtToken;


	public JwtResponse(UserDto user, String jwtToken) {
		this.user = user;
		this.jwtToken = jwtToken;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
}
