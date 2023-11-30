package com.ecommerce.dao;

import org.springframework.stereotype.Repository;

import com.ecommerce.entity.User;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface UserDao extends CrudRepository<User, String> {

}
