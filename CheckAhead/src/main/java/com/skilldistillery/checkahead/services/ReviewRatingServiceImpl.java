package com.skilldistillery.checkahead.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.checkahead.entities.Rating;
import com.skilldistillery.checkahead.entities.Review;
import com.skilldistillery.checkahead.entities.ReviewRating;
import com.skilldistillery.checkahead.entities.ReviewRatingId;
import com.skilldistillery.checkahead.repositories.RatingRepository;
import com.skilldistillery.checkahead.repositories.ReviewRatingRepository;
import com.skilldistillery.checkahead.repositories.ReviewRepository;
import com.skilldistillery.checkahead.repositories.UserRepository;

//abbreviated ReviewRating to rr or RR
@Service
public class ReviewRatingServiceImpl implements ReviewRatingService {
	
	@Autowired
	ReviewRatingRepository rrRepo;
	@Autowired
	ReviewRepository reviewRepo;
	@Autowired
	RatingRepository ratingRepo;
	@Autowired
	UserRepository userRepo;
	
	@Override
	public List<ReviewRating> findAllRRs() {
		return rrRepo.findAll();
	}
	
	@Override
	public ReviewRating createRR(int reviewId, int ratingId, ReviewRating rr, String username) {
//		User user = userRepo.findByUsername(username);
		try {
			Optional<Review> review = reviewRepo.findById(reviewId);
			if (review.isPresent()) {
				rr.setReview(review.get());
			}
			else {
				return null;
			}
			Optional<Rating> rating = ratingRepo.findById(ratingId);
			if (rating.isPresent()) {
				rr.setRating(rating.get());
			}
			else {
				return null;
			}
			if(rr.getReview().getUser().getUsername().equals(username)){
				ReviewRating newrr = rrRepo.saveAndFlush(rr);
				if (newrr != null) {
					return newrr;
				}
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}			
	}
	
	@Override
	public ReviewRating updateRR(int id, ReviewRating rr, String username) {
		Optional<ReviewRating> oldRR = rrRepo.findById(rr.getId());
		ReviewRating managedRR = null;
		if (oldRR.isPresent()) {
			managedRR = oldRR.get();
			managedRR.setRatingValue(rr.getRatingValue());
			if(managedRR.getReview().getUser().getUsername().equals(username)){
				return rrRepo.saveAndFlush(managedRR);							
			}
		}
		return null;
	}
	
	@Override
	public boolean deleteRR(int reviewId, int ratingId, String username) {
		boolean answer = false;
		ReviewRatingId rrId = new ReviewRatingId(reviewId,ratingId);
		Optional<ReviewRating> rr = rrRepo.findById(rrId);
		if (rr.isPresent() && rr.get().getReview().getUser().getUsername().equals(username)) {
			rrRepo.deleteById(rrId);
			answer = true;
		}
		return answer;
	}

	@Override
	public List<ReviewRating> findByLocation(int locationId) {
		return rrRepo.findByReviewLocationId(locationId);
	}

}
