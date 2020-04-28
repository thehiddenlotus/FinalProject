package com.skilldistillery.checkahead.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.checkahead.entities.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

}
