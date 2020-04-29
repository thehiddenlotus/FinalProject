package com.skilldistillery.checkahead.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.skilldistillery.checkahead.entities.Rating;
import com.skilldistillery.checkahead.repositories.RatingRepository;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	private RatingRepository rateRepo;
	


	@Override
	public Rating updateRating(Rating rating,int id) {
		Optional<Rating> oldRating = rateRepo.findById(id);
		Rating newRating = null;
		if(oldRating.isPresent()) {
			newRating = oldRating.get();
			newRating.setId(id);
			newRating.setCategory(rating.getCategory());
			return rateRepo.saveAndFlush(rating);
		}
		return null;
	}

	@Override
	public boolean deleteRating(int id) {
		boolean answer = false;
		Optional<Rating> rating = rateRepo.findById(id);
		if (rating.isPresent()) {
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
	public Rating createRating(Rating rating) {

		Rating newRating = rateRepo.saveAndFlush(rating);
		if (newRating != null) {
			return newRating;
		} else {
			return null;
		}

	}
	
	
}
