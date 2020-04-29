package com.skilldistillery.checkahead.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.checkahead.entities.Address;
import com.skilldistillery.checkahead.entities.User;
import com.skilldistillery.checkahead.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{

		@Autowired
		UserRepository userRepo;


		@Override
		public List<User> findAllUsers() {
			return userRepo.findAll();
		}

		@Override
		public User findUserById(int id) {
			Optional<User> user = userRepo.findById(id);
			if(user.isPresent()) {
				return user.get();
			}
			else {
				return null;
			}
		}

		@Override
		public User findUserByName(String username) {
			User user = userRepo.findByUsername(username);
			if(user != null) {
				return user;
			}
			else {
				return null;
			}
		}

		@Override
		public User createUser(User user, Address address) {
			user.setAddress(address);
			User newUser = userRepo.saveAndFlush(user);
			if (newUser != null) {
				return newUser;
			}
				return null;	
		}

		@Override
		public User updateUser(int id, User user) {
			Optional<User> opt = userRepo.findById(id);
			if (opt.isPresent()) {
				User managed = opt.get();
				managed.setId(id);
				managed.setUsername(user.getUsername());
				managed.setEmail(user.getEmail());
				managed.setRole(user.getRole());
				managed.setActive(user.isActive());
				managed.setDateUpdated(user.getDateUpdated());
				managed.setAddress(user.getAddress());
				return userRepo.saveAndFlush(managed);
			}
			return null;
		}

		@Override
		public boolean deleteUser(int id) {
			boolean result = false;
			Optional<User> user = userRepo.findById(id);
			if (user.isPresent()) {
				userRepo.deleteById(id);
				result = true;
			}
			
			return result;
		}
}
