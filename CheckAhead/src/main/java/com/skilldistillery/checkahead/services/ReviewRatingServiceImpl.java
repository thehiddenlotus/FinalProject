package com.skilldistillery.checkahead.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.skilldistillery.checkahead.repositories.ReviewRatingRepository;

public class ReviewRatingServiceImpl implements ReviewRatingService {
	
	@Autowired
	ReviewRatingRepository reviewRatingRepo;

}
