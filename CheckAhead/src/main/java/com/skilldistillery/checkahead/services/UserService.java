package com.skilldistillery.checkahead.services;

import java.util.List;

import com.skilldistillery.checkahead.entities.User;

public interface UserService {

	List<User> findAllUsers();

	User findUserById(int id);

	List<User> findUserByName(String username);

	List<User> findUserByAddressId(int id);

	User createUser(User user);

	User updateUser(User user);

	boolean deleteUser(int id);

}
