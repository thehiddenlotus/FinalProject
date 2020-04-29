package com.skilldistillery.checkahead.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		public List<User> findUserByName(String username) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public User createUser(User user) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public User updateUser(User user) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean deleteUser(int id) {
			// TODO Auto-generated method stub
			return false;
		}
}
