package com.skilldistillery.checkahead.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.checkahead.entities.User;
import com.skilldistillery.checkahead.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{

		@Autowired
		UserRepository userRepo;

}
