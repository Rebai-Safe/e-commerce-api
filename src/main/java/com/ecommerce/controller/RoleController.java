package com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.entity.Role;
import com.ecommerce.service.RoleService;

@RestController
public class RoleController {
	
	@Autowired 
	private RoleService roleService;
	
	@PostMapping({"/createNewRole"})
	@ResponseBody
	public Role createNewRole(@RequestBody Role role) {
		System.out.println("in request body");
		return roleService.createNewRole(role);
	}

}
