package com.skilldistillery.checkahead.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.skilldistillery.checkahead.repositories.UserRepository;

public class UserServiceImpl implements UserService{

		@Autowired
		UserRepository userRepo;
}