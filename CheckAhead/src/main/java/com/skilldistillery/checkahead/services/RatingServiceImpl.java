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
	public Rating createRating(Rating rating) {

		return rateRepo.saveAndFlush(rating);
	}

	@Override
	public Rating updateRating(Rating rating) {
		if(rateRepo.findById(rating.getId())!= null) {
			return rateRepo.saveAndFlush(rating);
		}else {
			
			return null;
		}
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
}
