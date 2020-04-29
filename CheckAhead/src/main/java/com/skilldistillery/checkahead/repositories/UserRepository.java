package com.skilldistillery.checkahead.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.checkahead.entities.User;

public interface UserRepository extends JpaRepository<User,Integer>{

	User findByUsername(String username);
}
