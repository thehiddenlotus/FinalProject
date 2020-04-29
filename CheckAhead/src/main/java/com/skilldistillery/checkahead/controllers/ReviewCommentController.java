package com.skilldistillery.checkahead.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.checkahead.entities.User;
import com.skilldistillery.checkahead.services.UserService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4220" })
public class ReviewCommentController {

	@Autowired
	UserService userServ;

	@GetMapping("users")
	public List<User> showAllUsers(HttpServletResponse resp){
		List<User> users = userServ.findAllUsers();
		if (users.size() > 0) {
			return users;
		}
		else {
			resp.setStatus(404);
			return null;
		}
	}
	@GetMapping("users/{id}")
	public User findAllUserById(@PathVariable Integer id, HttpServletResponse resp) {
		User user = userServ.findUserById(id);
		if(user != null) {
			return user;
		}else {
			resp.setStatus(404);
			return null;
		}
	}
	
//	@GetMapping("users/username/{username}")
//	public User findUserByUsername(@PathVariable String username) {
//	return null;
//	}
	
	@PostMapping("users")
	public User createNewUser(@RequestBody User user, HttpServletResponse resp){
		User newUser = userServ.createUser(user, user.getAddress());
		if (newUser != null) {
			return newUser;
		}
		else {
			resp.setStatus(404);
			return null;
		}
	}
	
	@PutMapping("users/{id}")
	public User updateExistingUser(@RequestBody User user, @PathVariable int id, HttpServletResponse response){
		User editUser = userServ.updateUser(id, user);
		if (editUser != null) {
			return editUser;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}
	
	@DeleteMapping("users/{id}")
	public void deleteUser(@PathVariable int id, HttpServletResponse resp){
		boolean result = false;
		try {
			result = userServ.deleteUser(id);
			if (result == true) {
				resp.setStatus(204);
			}
		} catch (Exception e) {			
			resp.setStatus(404);
		}
	}
	
}
