package com.skilldistillery.checkahead.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.checkahead.entities.Rating;
import com.skilldistillery.checkahead.services.RatingService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4220" })
public class RatingController {

	@Autowired
	private RatingService ratingServ;
	
	@GetMapping("ratings")
	public List<Rating> index() {
		return ratingServ.findAllRatings();
	}

	@GetMapping("ratings/{id}")
	public Rating getRatingById(
			@PathVariable Integer id, 
			HttpServletResponse response
			) {
		Rating rating = ratingServ.getRatingById(id);
		if (rating != null) {
			return rating;
		} else {

			response.setStatus(404);
			return null;
		}
	}

	@PostMapping("ratings")
	public Rating createRating(
			@RequestBody Rating rating, 
			HttpServletResponse response,
			Principal principal
			) {
		Rating newRating = ratingServ.createRating(rating, principal.getName());
		if (newRating != null) {
			return newRating;
		} else {
			response.setStatus(404);
			return null;
		}
	}

	@PutMapping("ratings/{id}")
	public Rating updateRating(
			@RequestBody Rating rating, 
			@PathVariable int id, 
			HttpServletResponse response,
			Principal principal
			) {
		Rating editRating = ratingServ.updateRating(rating, id, principal.getName());
		if (editRating != null) {
			return editRating;
		} else {
			response.setStatus(404);
			return null;
		}
	}
	@DeleteMapping("ratings/{id}")
	public void deleteRating(
			@PathVariable int id, 
			HttpServletResponse response,
			Principal principal
			) {
		boolean deleted = false;
		try {
			deleted = ratingServ.deleteRating(id, principal.getName());
			if(deleted == true) {
				response.setStatus(204);
			}
		}catch (Exception e) {
			response.setStatus(404);
		}
	}
}
