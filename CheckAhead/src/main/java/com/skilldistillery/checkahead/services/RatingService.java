package com.skilldistillery.checkahead.services;



import com.skilldistillery.checkahead.entities.Rating;

public interface RatingService {
	
	Rating getRatingById(int id);

	Rating createRating(Rating rating, String username);
	
	Rating updateRating(Rating rating, int id, String username);
	
	boolean deleteRating(int id, String username);
}
