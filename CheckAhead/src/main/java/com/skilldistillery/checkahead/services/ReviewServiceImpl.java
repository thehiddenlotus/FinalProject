package com.skilldistillery.checkahead.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.checkahead.entities.Review;
import com.skilldistillery.checkahead.entities.User;
import com.skilldistillery.checkahead.repositories.ReviewRepository;
import com.skilldistillery.checkahead.repositories.UserRepository;

@Transactional
@Service
public class ReviewServiceImpl implements ReviewService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private ReviewRepository reviewRepo;

	@Autowired
	private UserRepository userRepo;

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

//	@Override
//	public Review createReview(String username, Review review) {
//		User user = userRepo.findByUsername(username);
//		if (user != null) {
//			review.setUser(user);
//			reviewRepo.saveAndFlush(review);
//		} else {
//			review = null;
//		}
//		return review;
//	}
	@Override
	public Review createReview(Review review) {
		em.persist(review);
		em.flush();
		return review;
	}


	
	@Override
	public Review updateReview(Integer reviewId, Review review) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteReview(Integer reviewId) {
		// TODO Auto-generated method stub
		return false;
	}

}