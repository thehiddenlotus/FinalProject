package com.skilldistillery.checkahead.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.checkahead.entities.Location;
import com.skilldistillery.checkahead.entities.Review;
import com.skilldistillery.checkahead.entities.User;
import com.skilldistillery.checkahead.repositories.LocationRepository;
import com.skilldistillery.checkahead.repositories.ReviewRepository;
import com.skilldistillery.checkahead.repositories.UserRepository;

@Transactional
@Service
public class ReviewServiceImpl implements ReviewService {
	
	
	@Autowired
	private ReviewRepository reviewRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private LocationRepository locRepo;

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
	public List<Review> findByLocation(Integer locId) {
		if (locRepo.findById(locId).isPresent()) {
			return reviewRepo.findByLocation_Id(locId);
		}
		return null;
	}
	
	@Override
	public List<Review> findByUser(Integer userId){
		Optional<User> opt = userRepo.findById(userId);
		if(opt.isPresent()) {
			return reviewRepo.findByUser_Username(opt.get().getUsername());
		}
		return null;
	}

	@Override
	public Review createReview(Review review, Integer locationId, String username) {
		User user = userRepo.findByUsername(username);
		Optional<Location> loc = locRepo.findById(locationId);
		if (user != null && loc.isPresent()) {
			review.setUser(user);
			review.setLocation(loc.get());
			review.setDateCreated(LocalDateTime.now());
//			if (review.getUser().getUsername().equals(username)) {
				reviewRepo.saveAndFlush(review);
//			}
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
//			managedReview.setUser(review.getUser());
//			managedReview.setLocation(review.getLocation());
			managedReview.setDateUpdated(LocalDateTime.now());
			if (managedReview.getUser().getUsername().equals(username) || userRepo.findByUsername(username).getRole().equals("admin")) {
				return reviewRepo.saveAndFlush(managedReview);
			}			
		}
		return null;
	}

	@Override
	public boolean deleteReview(Integer reviewId, String username) {
		boolean answer = false;
		Optional<Review> optReview = reviewRepo.findById(reviewId);
		if (optReview.isPresent() && 
				(optReview.get().getUser().getUsername().equals(username)
				|| userRepo.findByUsername(username).getRole().equals("admin")
			)) {
			reviewRepo.deleteById(reviewId);
			answer = true;
		}
		return answer;
	}

}