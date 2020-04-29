package com.skilldistillery.checkahead.services;

import java.util.List;

import com.skilldistillery.checkahead.entities.ReviewRating;

public interface ReviewRatingService {

	List<ReviewRating> findAllRRs();

	ReviewRating createRR(int reviewId, int ratingId, ReviewRating rr);

	boolean deleteRR(int reviewId, int ratingId);

	ReviewRating updateRR(int id, ReviewRating rr);
	
	List<ReviewRating> findByLocation(int locationId);

}
