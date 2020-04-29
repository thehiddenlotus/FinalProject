package com.skilldistillery.checkahead.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.checkahead.entities.Location;
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
	
	@PostMapping("reviewratings/{ratingid}/{reviewid}")
	public ReviewRating createNewRR(
			@PathVariable Integer ratingid, 
			@PathVariable Integer reviewid, 
			@RequestBody ReviewRating rr, 
			HttpServletResponse response){
		ReviewRating newRR = rrServ.createRR(reviewid, ratingid, rr);
		if (newRR != null) {
			return newRR;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}
	
	@PutMapping("reviewratings/{id}")
	public ReviewRating updateExistingRR(@RequestBody ReviewRating rr, @PathVariable int id, HttpServletResponse response){
		ReviewRating editRR = rrServ.updateRR(id, rr);
		if (editRR != null) {
			return editRR;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}

}
