package com.ecommerce.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.dao.RoleDao;
import com.ecommerce.entity.Role;

@Service
public class RoleService {

	private final RoleDao  roleDao;

	public RoleService(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public Role createNewRole(Role role) {
		return roleDao.save(role);
	}

}
