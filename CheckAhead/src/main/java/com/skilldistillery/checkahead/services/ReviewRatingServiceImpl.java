package com.skilldistillery.checkahead.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.checkahead.entities.Rating;
import com.skilldistillery.checkahead.entities.Review;
import com.skilldistillery.checkahead.entities.ReviewRating;
import com.skilldistillery.checkahead.repositories.RatingRepository;
import com.skilldistillery.checkahead.repositories.ReviewRatingRepository;
import com.skilldistillery.checkahead.repositories.ReviewRepository;

//abbreviated ReviewRating to rr or RR
@Service
public class ReviewRatingServiceImpl implements ReviewRatingService {
	
	@Autowired
	ReviewRatingRepository rrRepo;
	@Autowired
	ReviewRepository reviewRepo;
	@Autowired
	RatingRepository ratingRepo;
	
	@Override
	public List<ReviewRating> findAllRRs() {
		return rrRepo.findAll();
	}
	
	@Override
	public ReviewRating createRR(int reviewId, int ratingId, ReviewRating rr) {
		
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
		ReviewRating newrr = rrRepo.saveAndFlush(rr);
		if (newrr != null) {
			return newrr;
		}
			return null;			
	}

}
