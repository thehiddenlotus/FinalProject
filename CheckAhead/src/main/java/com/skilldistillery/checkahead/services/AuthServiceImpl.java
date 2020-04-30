package com.skilldistillery.checkahead.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.skilldistillery.checkahead.entities.User;
import com.skilldistillery.checkahead.repositories.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UserRepository repo;
	
	@Override
	public User register(User user) {
		String encoded = encoder.encode(user.getPassword());
		user.setPassword(encoded);
		user.setActive(true);
		user.setRole("standard");
		repo.saveAndFlush(user);
		return user;
	}

}
