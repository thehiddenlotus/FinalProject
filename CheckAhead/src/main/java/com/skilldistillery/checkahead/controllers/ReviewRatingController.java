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

import com.skilldistillery.checkahead.entities.ReviewRating;
import com.skilldistillery.checkahead.services.ReviewRatingService;

@RestController
@RequestMapping ("api")
@CrossOrigin({"*", "http://localhost:4220"}) // Angular local port
public class ReviewRatingController {
	
	@Autowired
	private ReviewRatingService rrServ;
	
	@GetMapping("reviewratings")
	public List<ReviewRating> getAllRRs(HttpServletResponse response){
		List<ReviewRating> rrs = rrServ.findAllRRs();
		if (rrs.size() > 0) {
			return rrs;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}
	
	@GetMapping("locations/{locId}/reviews/reviewratings")
	public List<ReviewRating> getRRsByLocation(@PathVariable int locId, HttpServletResponse response){
		List<ReviewRating> rrs = rrServ.findByLocation(locId);
		if (rrs.size() > 0) {
			return rrs;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}

	@GetMapping("locations/reviews/{revId}/reviewratings")
	public List<ReviewRating> getRRsByReviews(@PathVariable int revId, HttpServletResponse response){
		List<ReviewRating> rrs = rrServ.findByReview(revId);
		if (rrs.size() > 0) {
			return rrs;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}
	
	@PostMapping("locations/reviews/{reviewid}/ratings/{ratingid}/reviewratings")
	public ReviewRating createNewRR(
			@PathVariable Integer ratingid, 
			@PathVariable Integer reviewid, 
			@RequestBody ReviewRating rr, 
			HttpServletResponse response,
			Principal principal
		){
		ReviewRating newRR = rrServ.createRR(reviewid, ratingid, rr, principal.getName());
		if (newRR != null) {
			return newRR;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}
	
	@PutMapping("reviewratings/{id}")
	public ReviewRating updateExistingRR(
			@RequestBody ReviewRating rr, 
			@PathVariable int id, 
			HttpServletResponse response,
			Principal principal
			){
		ReviewRating editRR = rrServ.updateRR(id, rr, principal.getName());
		if (editRR != null) {
			return editRR;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}
	
	@DeleteMapping("reviewratings/{reviewid}/{ratingid}")
	public void deleteRR(
			@PathVariable int reviewid, 
			@PathVariable int ratingid, 
			HttpServletResponse response,
			Principal principal
		){
		boolean deleted = false;
		try {
			deleted = rrServ.deleteRR(reviewid, ratingid, principal.getName());
			if (deleted == true) {
				response.setStatus(204);
			}
		} catch (Exception e) {			
			response.setStatus(404);
		}
	}

}
