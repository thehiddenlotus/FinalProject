package com.skilldistillery.checkahead.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public Review show(@PathVariable("reviewId") Integer reviewId, HttpServletRequest request, HttpServletResponse response, String username) {
		Review review = reviewSvc.findById(username, reviewId);
		if (review == null) {
			response.setStatus(404);
		} else {
			response.setStatus(201);
		}
		return review;
	}
	

}
