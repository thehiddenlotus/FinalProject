package com.skilldistillery.checkahead.services;

import java.util.List;

import com.skilldistillery.checkahead.entities.Location;
import com.skilldistillery.checkahead.entities.Review;
import com.skilldistillery.checkahead.entities.User;

public interface ReviewService {
	List<Review> findAllReviews();
	
	Review findById(Integer reviewId);
	
	Review createReview(User user, Review review, Location location);
	
	Review updateReview(Integer reviewId, Review review);
	
	boolean deleteReview(Integer reviewId);

}
