package com.skilldistillery.checkahead.services;

import java.util.List;

import com.skilldistillery.checkahead.entities.ReviewRating;

public interface ReviewRatingService {

	List<ReviewRating> findAllRRs();

	ReviewRating createRR(int reviewId, int ratingId, ReviewRating rr, String username);

	boolean deleteRR(int reviewId, int ratingId, String username);

	ReviewRating updateRR(int id, ReviewRating rr, String username);
	
	List<ReviewRating> findByLocation(int locationId);

	List<ReviewRating> findByReview(int reviewId);

}
