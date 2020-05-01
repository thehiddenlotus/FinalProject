package com.skilldistillery.checkahead.services;

import java.util.List;

import com.skilldistillery.checkahead.entities.Review;

public interface ReviewService {
	List<Review> findAllReviews();
	
	Review findById(Integer reviewId);
	
	Review createReview(Review review, Integer locationId, String username);
	
	Review updateReview(Integer reviewId, Review review, String username);
	
	boolean deleteReview(Integer reviewId, String username);

	public List<Review> findByLocation(Integer locId);

	public List<Review> findByUser(Integer userId);
}
