package com.skilldistillery.checkahead.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.checkahead.entities.Location;
import com.skilldistillery.checkahead.entities.Review;
import com.skilldistillery.checkahead.entities.User;
import com.skilldistillery.checkahead.repositories.ReviewRepository;

@Transactional
@Service
public class ReviewServiceImpl implements ReviewService {
	
	
	@Autowired
	private ReviewRepository reviewRepo;

//	@Autowired
//	private UserRepository userRepo;

	@Override
	public List<Review> findAllReviews() {
		return reviewRepo.findAll();
	}

	@Override
	public Review findById(Integer reviewId) {
		Optional<Review> optReview = reviewRepo.findById(reviewId);
		if (optReview.isPresent()) {
			Review foundReview = optReview.get();
			return foundReview;
		}
		return null;
	}

	@Override
	public Review createReview(User user, Review review, Location location, String username) {
//		User user = userRepo.findByUsername(username);
		if (user != null) {
			review.setUser(user);
			review.setLocation(location);
			if (review.getUser().getUsername().equals(username)) {
				reviewRepo.saveAndFlush(review);
			}
		} else {
			review = null;
		}
		return review;
	}
//	@Override
//	public Review createReview(Review review) {
//		
//		return review;
//	}


	
	@Override
	public Review updateReview(Integer reviewId, Review review, String username) {
		Optional<Review> optReview = reviewRepo.findById(reviewId);
		Review managedReview = null;
		if (optReview.isPresent()) {
			managedReview = optReview.get();
			managedReview.setId(reviewId);
			managedReview.setContent(review.getContent());
			managedReview.setUser(review.getUser());
			managedReview.setLocation(review.getLocation());
			if (managedReview.getUser().getUsername().equals(username)) {
				return reviewRepo.saveAndFlush(managedReview);
			}			
		}
		return null;
	}

	@Override
	public boolean deleteReview(Integer reviewId, String username) {
		boolean answer = false;
		Optional<Review> optReview = reviewRepo.findById(reviewId);
		if (optReview.isPresent() && optReview.get().getUser().getUsername().equals(username)) {
			reviewRepo.deleteById(reviewId);
			answer = true;
		}
		return answer;
	}

}