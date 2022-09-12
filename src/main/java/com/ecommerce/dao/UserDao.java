package com.ecommerce.dao;

import org.springframework.stereotype.Repository;

import com.ecommerce.entity.Users;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface UserDao extends CrudRepository<Users, String> {

}
