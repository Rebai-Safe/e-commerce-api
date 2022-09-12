package com.ecommerce.entity;

public class JwtRequest {

	private String userName;
	private String userPassword;
	
	public String getUserName() {
		return userName;
	}
	/*Basically, Spring uses getter and setter to set the properties of the the bean object.
	 *  And it takes the property of the JSON object, 
	 *  matches it with the setter of the same name */
	 
	public void setUserName(String username) {
		this.userName = username;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	
	
}
