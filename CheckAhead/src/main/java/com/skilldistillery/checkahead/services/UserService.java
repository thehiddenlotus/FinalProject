package com.skilldistillery.checkahead.services;

import java.util.List;

import com.skilldistillery.checkahead.entities.Address;
import com.skilldistillery.checkahead.entities.User;

public interface UserService {

	List<User> findAllUsers();

	User findUserById(int id);

	User findUserByName(String username);

	User createUser(User user, Address address);

	User updateUser(int id, User user);

	boolean deleteUser(int id);

}
