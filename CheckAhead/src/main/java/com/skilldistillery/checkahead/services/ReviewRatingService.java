package com.skilldistillery.checkahead.services;

import java.util.List;

import com.skilldistillery.checkahead.entities.ReviewRating;

public interface ReviewRatingService {

	List<ReviewRating> findAllRRs();

	ReviewRating createRR(int reviewId, int ratingId, ReviewRating rr);

}
