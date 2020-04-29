package com.skilldistillery.checkahead.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.checkahead.entities.ReviewRating;
import com.skilldistillery.checkahead.entities.ReviewRatingId;

public interface ReviewRatingRepository extends JpaRepository<ReviewRating, ReviewRatingId> {
	
		List<ReviewRating> findByReviewLocationId(int locationId);
}
