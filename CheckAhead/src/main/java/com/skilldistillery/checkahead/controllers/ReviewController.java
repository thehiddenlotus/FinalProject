package com.skilldistillery.checkahead.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
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

import com.skilldistillery.checkahead.entities.Review;
import com.skilldistillery.checkahead.services.ReviewService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4220" }) // Angular local port
public class ReviewController {
	@Autowired
	private ReviewService reviewSvc;

	@GetMapping("reviews")
	public List<Review> index() {
		return reviewSvc.findAllReviews();
	}
	
	@GetMapping("reviews/{reviewId}")
	public Review show(@PathVariable("reviewId") Integer reviewId, HttpServletRequest request, HttpServletResponse response) {
		Review review = reviewSvc.findById(reviewId);
		if (review == null) {
			response.setStatus(404);
		} else {
			response.setStatus(201);
		}
		return review;
	}
	
	@PostMapping("reviews")
    public Review createReview(@RequestBody Review review,HttpServletRequest request, HttpServletResponse response) { 
        try {
           review = reviewSvc.createReview(review.getUser(), review, review.getLocation());
           if(review == null) {
               response.setStatus(400);
               return null;
           }else {
               response.setStatus(201);
               return review;
           }
       } catch (Exception e) {
           e.printStackTrace();
           response.setStatus(400);
           return null;
       }
        
	}
	
	@PutMapping("reviews/{reviewId}")
	public Review updateReview(@RequestBody Review review, @PathVariable Integer reviewId, HttpServletResponse response) {
		Review editReview = reviewSvc.updateReview(reviewId, review);
		if (editReview != null) {
			return editReview;
		} else {
			response.setStatus(404);
			return null;
		}
	}
	
	@DeleteMapping("reviews/{reviewId}")
	public void deleteLocation(@PathVariable Integer reviewId, HttpServletResponse response){
		boolean deleted = false;
		try {
			deleted = reviewSvc.deleteReview(reviewId);
			if (deleted == true) {
				response.setStatus(204);
			}
		} catch (Exception e) {			
			response.setStatus(404);
		}
	}
	

}
