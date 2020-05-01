package com.skilldistillery.checkahead.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.checkahead.entities.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

	List<Review> findByUser_Username(String username);

	List<Review> findByLocation_Id(int id);
	
	Review findByUser_UsernameAndId(String username, int id);
}
