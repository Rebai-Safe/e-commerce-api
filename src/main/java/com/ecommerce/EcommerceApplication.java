package com.ecommerce;

import com.ecommerce.dao.RoleDao;
import com.ecommerce.dao.UserDao;
import com.ecommerce.entity.Role;
import com.ecommerce.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@SpringBootApplication 
public class EcommerceApplication {

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

	//@Bean
	CommandLineRunner commandLineRunner(RoleDao roleDao,
										UserDao userDao){

		return  args -> {
			Stream.of("ADMIN", "USER").forEach(
					roleN -> {
						Role role = new Role();
						role.setRoleName(roleN);
						role.setRoleDescription("description");
						roleDao.save(role);
					});

			Stream.of("safe", "chayma").forEach(nameU -> {

				Users user = new Users();
				user.setUserName(nameU);
				user.setUserPassword(getEncodedPassword("password"));
				Set<Role> roles = new HashSet<>();
				if(nameU.equals("safe")){
					roles.add(roleDao.findById("ADMIN").get());
				}else{
					roles.add(roleDao.findById("USER").get());
				}
				user.setRoles(roles);
				userDao.save(user);
			});
		};
	}

	public String getEncodedPassword(String password) {
		return passwordEncoder.encode(password);
	}
}
