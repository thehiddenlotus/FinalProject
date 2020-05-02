package com.skilldistillery.checkahead.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.skilldistillery.checkahead.entities.User;
import com.skilldistillery.checkahead.repositories.AddressRepository;
import com.skilldistillery.checkahead.repositories.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UserRepository repo;

	@Autowired
	private AddressRepository aRepo;
	
	@Override
	public User register(User user) {
		user.setAddress(aRepo.saveAndFlush(user.getAddress()));
		String encoded = encoder.encode(user.getPassword());
		user.setPassword(encoded);
		user.setActive(true);
		user.setRole("user");
		user.setDateCreated(LocalDateTime.now());
		repo.saveAndFlush(user);
		return user;
	}

}
