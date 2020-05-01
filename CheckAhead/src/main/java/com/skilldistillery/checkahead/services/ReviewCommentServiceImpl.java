package com.skilldistillery.checkahead.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.checkahead.entities.Review;
import com.skilldistillery.checkahead.entities.ReviewComment;
import com.skilldistillery.checkahead.entities.User;
import com.skilldistillery.checkahead.repositories.ReviewCommentRepository;
import com.skilldistillery.checkahead.repositories.ReviewRepository;
import com.skilldistillery.checkahead.repositories.UserRepository;

@Service
public class ReviewCommentServiceImpl implements ReviewCommentService {

	@Autowired
	private ReviewCommentRepository repo;
	@Autowired
	private UserRepository uRepo;
	@Autowired
	private ReviewRepository rRepo;

	@Override
	public List<ReviewComment> findAllComments() {
		return repo.findAll();
	}

	@Override
	public ReviewComment findCommentById(int id) {
		Optional<ReviewComment> comment = repo.findById(id);
		if(comment.isPresent()) {
			return comment.get();
		}
		else {
			return null;
		}
	}

	@Override
	public ReviewComment createComment(ReviewComment comment, int reviewId, String username) {
		Optional<Review> rOpt = rRepo.findById(reviewId);
		if (rOpt.isPresent()) {
			comment.setReview(rOpt.get());			
		}else {
			return null;
		}
		User managed = uRepo.findByUsername(username);
		if (managed != null) {
			comment.setUser(managed);	
		}else {
			return null;
		}
		comment.setCreatedAt(LocalDateTime.now());
		comment.setActive(true);
		ReviewComment newComment = null;
		if (comment.getUser().getUsername().equals(username)) {
			newComment = repo.saveAndFlush(comment);
		}
		return newComment;
	}

	@Override
	public ReviewComment updateComment(int id, ReviewComment comment, String username) {
		Optional<ReviewComment> opt = repo.findById(id);
		if (opt.isPresent()) {
			ReviewComment managed = opt.get();
			managed.setId(id);
			managed.setContent(comment.getContent());
			managed.setReviewRating(comment.getReviewRating());
			managed.setActive(comment.isActive());
			managed.setUpdatedAt(LocalDateTime.now());
			if (managed.getUser().getUsername().equals(username)) {
				return repo.saveAndFlush(managed);
			}
		}
		return null;
	}

	@Override
	public boolean deleteComment(int id, String username) {
		boolean result = false;
		Optional<ReviewComment> comment = repo.findById(id);
		if (comment.isPresent() && comment.get().getUser().getUsername().equals(username)) {
			repo.deleteById(id);
			result = true;
		}
		return result;
	}
}
