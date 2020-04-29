package com.skilldistillery.checkahead.services;

import java.util.List;

import com.skilldistillery.checkahead.entities.ReviewComment;

public interface ReviewCommentService {

	List<ReviewComment> findAllComments();

	ReviewComment findCommentById(int id);

	ReviewComment createComment(ReviewComment comment, int userId, int reviewId);

	ReviewComment updateComment(int id, ReviewComment comment);

	boolean deleteComment(int id);
	
}
