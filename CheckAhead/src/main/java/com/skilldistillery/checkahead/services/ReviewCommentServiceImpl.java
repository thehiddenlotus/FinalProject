package com.skilldistillery.checkahead.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.checkahead.repositories.ReviewCommentRepository;

@Service
public class ReviewCommentServiceImpl implements ReviewCommentService {

	@Autowired
	private ReviewCommentRepository repo;
}
