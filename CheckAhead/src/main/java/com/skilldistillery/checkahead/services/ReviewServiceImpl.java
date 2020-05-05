package com.skilldistillery.checkahead.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.checkahead.entities.Location;
import com.skilldistillery.checkahead.entities.Review;
import com.skilldistillery.checkahead.entities.ReviewComment;
import com.skilldistillery.checkahead.entities.ReviewRating;
import com.skilldistillery.checkahead.entities.User;
import com.skilldistillery.checkahead.repositories.LocationRepository;
import com.skilldistillery.checkahead.repositories.RatingRepository;
import com.skilldistillery.checkahead.repositories.ReviewCommentRepository;
import com.skilldistillery.checkahead.repositories.ReviewRatingRepository;
import com.skilldistillery.checkahead.repositories.ReviewRepository;
import com.skilldistillery.checkahead.repositories.UserRepository;

@Transactional
@Service
public class ReviewServiceImpl implements ReviewService {
	
	
	@Autowired
	private ReviewRepository reviewRepo;

	@Autowired
	private ReviewRatingRepository rrRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private RatingRepository ratingRepo;

	@Autowired
	private LocationRepository locRepo;
	
	@Autowired
	private ReviewCommentRepository comRepo;

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
	public Review createReview(Review review, Integer locationId, Integer cleanRating, Integer trafficRating, Integer checkoutRating, Integer stockRating, String username) {
		User user = userRepo.findByUsername(username);
		Optional<Location> loc = locRepo.findById(locationId);
		if (user != null && loc.isPresent()) {
			review.setUser(user);
			review.setLocation(loc.get());
			review.setDateCreated(LocalDateTime.now());
//			if (review.getUser().getUsername().equals(username)) {
			reviewRepo.saveAndFlush(review);
			ReviewRating clean = new ReviewRating(cleanRating, review, ratingRepo.getOne(1));
			ReviewRating traffic = new ReviewRating(trafficRating, review, ratingRepo.getOne(2));
			ReviewRating checkout = new ReviewRating(checkoutRating, review, ratingRepo.getOne(3));
			ReviewRating stock = new ReviewRating(stockRating, review, ratingRepo.getOne(4));
			rrRepo.saveAndFlush(clean);
			rrRepo.saveAndFlush(traffic);
			rrRepo.saveAndFlush(checkout);
			rrRepo.saveAndFlush(stock);
			List<ReviewRating> ratings = new ArrayList<>();
			ratings.add(clean);
			ratings.add(traffic);
			ratings.add(checkout);
			ratings.add(stock);
			review.setRatings(ratings);
			return reviewRepo.saveAndFlush(review);
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
		System.out.println(reviewId);
		boolean answer = false;
		Optional<Review> optReview = reviewRepo.findById(reviewId);
		if (optReview.isPresent() && 
				(optReview.get().getUser().getUsername().equals(username)
				|| userRepo.findByUsername(username).getRole().equals("admin")
			)) {
			Review rev = optReview.get();
			System.out.println(rev);
			for (ReviewRating rating : rev.getRatings()) {
				try {
//					rrSvc.deleteRR(rating.getReview().getId(), rating.getRating().getId(), principal.getName());
					rrRepo.deleteById(rating.getId());
				} catch (Exception e) {			
					e.printStackTrace();
				}
			}
			for (ReviewComment comment : rev.getComments()) {
				try {
//					comSvc.deleteComment(comment.getId(), principal.getName());
					comRepo.deleteById(comment.getId());
				} catch (Exception e) {			
					e.printStackTrace();
				}
			}
			System.out.println("DELETING REVIEW");
//			reviewRepo.deleteById(reviewId);
			reviewRepo.delete(optReview.get());
			System.out.println("DONE DELETING");
			answer = true;
		}
		return answer;
	}

}