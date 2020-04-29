package com.skilldistillery.checkahead.services;

import java.util.List;

import com.skilldistillery.checkahead.entities.Review;
import com.skilldistillery.checkahead.entities.ReviewComment;
import com.skilldistillery.checkahead.entities.User;

public interface ReviewCommentService {

	List<ReviewComment> findAllComments();

	ReviewComment findCommentById(int id);

	ReviewComment createComment(ReviewComment comment, User user, Review review);

	ReviewComment updateComment(int id, ReviewComment comment);

	boolean deleteComment(int id);
	
}
