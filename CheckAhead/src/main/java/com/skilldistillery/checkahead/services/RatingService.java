package com.skilldistillery.checkahead.services;



import com.skilldistillery.checkahead.entities.Rating;

public interface RatingService {

	Rating createRating(Rating rating);
	
	Rating updateRating(Rating rating);
	
	boolean deleteRating(int id);
}
