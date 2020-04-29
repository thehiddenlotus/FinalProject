package com.skilldistillery.checkahead.services;

import java.util.List;

import com.skilldistillery.checkahead.entities.Review;

public interface ReviewService {
	List<Review> findAllReviews();
	
	Review findById(Integer reviewId);
	
	Review createReview(String username, Review review);
	
	Review updateReview(Integer reviewId, Review review);
	
	boolean deleteReview(Integer reviewId);

}
