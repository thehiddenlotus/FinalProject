package com.skilldistillery.checkahead.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.checkahead.entities.Rating;

public interface RatingRepository extends JpaRepository<Rating, Integer> {

}
