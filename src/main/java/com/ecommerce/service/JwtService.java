package com.ecommerce.service;

import com.ecommerce.dao.UserDao;
import com.ecommerce.dto.UserDto;
import com.ecommerce.entity.User;

import com.ecommerce.mapper.UserMapper;
import com.ecommerce.model.JwtRequest;
import com.ecommerce.model.JwtResponse;
import com.ecommerce.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class JwtService implements UserDetailsService {


	private final UserDao userDao;
	private final JwtUtil jwtUtil;
	private final UserMapper userMapper;
	private final AuthenticationManager authenticationManager;
	private static final Logger LOGGER = LoggerFactory.getLogger(JwtService.class);

	public JwtService(UserDao userDao, JwtUtil jwtUtil, UserMapper userMapper, AuthenticationManager authenticationManager) {
		this.userDao = userDao;
		this.jwtUtil = jwtUtil;
		this.userMapper = userMapper;
		this.authenticationManager = authenticationManager;
	}

	public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
		String userName = jwtRequest.getUserName();
		String userPassword = jwtRequest.getUserPassword();
		authenticate(userName, userPassword);
		
		final UserDetails userDetails = loadUserByUsername(userName);
		
		String newGeneratedToken = jwtUtil.generateToken(userDetails);
		
		User user = userDao.findById(userName).get();
		UserDto userDto = userMapper.userToUserDto(user);
		
		return new JwtResponse(userDto, newGeneratedToken);
	}

	private void authenticate(String username, String userPassword) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, userPassword));
		} catch (Exception e) {
			LOGGER.info("Bad credentials from user");
			throw new com.ecommerce.exception.BadCredentialsException("bad credentials from user");
		}
//		catch (DisabledException e) {
//			throw new Exception("user is disabled");
//		} catch (BadCredentialsException e) {
//			LOGGER.info("Bad credentials from user");
//			throw new com.ecommerce.exception.BadCredentialsException("bad credentials from user");
//		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findById(username).get();
		if (user != null) {
			return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getUserPassword(), getAuthorities(user));
		}
		else {
			throw new UsernameNotFoundException("Username is not valid");
		}
	}

	private Set<SimpleGrantedAuthority> getAuthorities(User user) {

		Set<SimpleGrantedAuthority> authorities = new HashSet<>();

		user.getRoles().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
		});

		return authorities;

	}
	
	
}
