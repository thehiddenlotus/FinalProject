package com.skilldistillery.checkahead.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.skilldistillery.checkahead.entities.Rating;
import com.skilldistillery.checkahead.repositories.RatingRepository;
import com.skilldistillery.checkahead.repositories.UserRepository;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	private RatingRepository rateRepo;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public Rating updateRating(Rating rating, int id, String username) {
		Optional<Rating> oldRating = rateRepo.findById(id);
		Rating newRating = null;
		if(oldRating.isPresent()) {
			newRating = oldRating.get();
			newRating.setId(id);
			newRating.setCategory(rating.getCategory());
			if (userRepo.findByUsername(username).getUsername().equals(username)) {
				return rateRepo.saveAndFlush(rating);
			}
		}
		return null;
	}

	@Override
	public boolean deleteRating(int id, String username) {
		boolean answer = false;
		Optional<Rating> rating = rateRepo.findById(id);
		if (rating.isPresent() && userRepo.findByUsername(username).getUsername().equals(username)) {
			rateRepo.deleteById(id);
			answer = true;
		}

		return answer;

	}

	@Override
	public Rating getRatingById(int id) {
		Optional<Rating> rating = rateRepo.findById(id);
		if (rating.isPresent()) {
			return rating.get();
		} else {

			return null;
		}
	}

	@Override
	public Rating createRating(Rating rating, String username) {
		Rating newRating = null;
		if (userRepo.findByUsername(username).getUsername().equals(username)) {
			newRating = rateRepo.saveAndFlush(rating);
		}
		return newRating;

	}
	
	
}
