package com.skilldistillery.checkahead.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.checkahead.entities.ReviewComment;

public interface ReviewCommentRepository extends JpaRepository<ReviewComment, Integer> {

}
