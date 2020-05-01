package com.skilldistillery.checkahead.services;

import java.util.List;

import com.skilldistillery.checkahead.entities.ReviewComment;

public interface ReviewCommentService {

	List<ReviewComment> findAllComments();

	ReviewComment findCommentById(int id);

	ReviewComment createComment(ReviewComment comment, int reviewId, String username);

	ReviewComment updateComment(int id, ReviewComment comment, String username);

	boolean deleteComment(int id, String username);
	
}
