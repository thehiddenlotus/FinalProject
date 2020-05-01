package com.skilldistillery.checkahead.services;

import java.util.List;
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
	public List<Rating> findAllRatings() {
		return rateRepo.findAll();
	}

	@Override
	public Rating updateRating(Rating rating, int id, String username) {
		if (userRepo.findByUsername(username).getRole().equals("admin")) {
			Optional<Rating> oldRating = rateRepo.findById(id);
			Rating newRating = null;
			if(oldRating.isPresent()) {
				newRating = oldRating.get();
				newRating.setId(id);
				newRating.setCategory(rating.getCategory());
				return rateRepo.saveAndFlush(newRating);
			}
		}
		return null;
	}

	@Override
	public boolean deleteRating(int id, String username) {
		boolean answer = false;
		if (userRepo.findByUsername(username).getRole().equals("admin")) {
			Optional<Rating> rating = rateRepo.findById(id);
			if (rating.isPresent() && userRepo.findByUsername(username).getRole().equals("admin")) {
				rateRepo.deleteById(id);
				answer = true;
			} 
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
		if (userRepo.findByUsername(username).getRole().equals("admin")) {

				newRating = rateRepo.saveAndFlush(rating);

		}
		return newRating;
	}
	
	
}
