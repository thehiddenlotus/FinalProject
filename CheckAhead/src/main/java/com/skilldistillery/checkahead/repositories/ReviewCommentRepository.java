package com.skilldistillery.checkahead.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.checkahead.entities.ReviewComment;

public interface ReviewCommentRepository extends JpaRepository<ReviewComment, Integer> {

	List<ReviewComment> findByReview_Id(int revId);

	List<ReviewComment> findByUser_Id(int userId);
}
