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
	

	private final RoleService roleService;

	public RoleController(RoleService roleService) {
		this.roleService = roleService;
	}

	@PostMapping({"/createNewRole"})
	@ResponseBody
	public Role createNewRole(@RequestBody Role role) {
		return roleService.createNewRole(role);
	}

}
