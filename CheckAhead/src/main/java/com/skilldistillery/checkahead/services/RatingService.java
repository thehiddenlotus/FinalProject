package com.skilldistillery.checkahead.services;



import com.skilldistillery.checkahead.entities.Rating;

public interface RatingService {
	
	Rating getRatingById(int id);

	Rating createRating(Rating rating);
	
	Rating updateRating(Rating rating, int id);
	
	boolean deleteRating(int id);
}
