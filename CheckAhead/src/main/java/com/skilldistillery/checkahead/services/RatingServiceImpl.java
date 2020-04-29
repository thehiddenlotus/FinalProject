package com.skilldistillery.checkahead.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.checkahead.entities.Rating;
import com.skilldistillery.checkahead.repositories.RatingRepository;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	private RatingRepository repo;

	@Override
	public Rating createRating(Rating rating) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rating updateRating(Rating rating) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteRating(int id) {
		// TODO Auto-generated method stub
		return false;
	}
}
