package com.skilldistillery.checkahead.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "review_comment")
public class ReviewComment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String content;
	
	@Column(name="date_created")
	private LocalDateTime createdAt;
	
	@Column(name="date_updated")
	private LocalDateTime updatedAt;
	
	@Column(name = "review_rating")
	private int reviewRating;
	
	public ReviewComment() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public int getReviewRating() {
		return reviewRating;
	}

	public void setReviewRating(int reviewRating) {
		this.reviewRating = reviewRating;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Review getReview() {
		return review;
	}

	public void setReview(Review review) {
		this.review = review;
	}

	private boolean active;
	
	@Column(name = "user_id")
	private User user;
	
	@Column(name = "review_id")
	private Review review;
	

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ReviewComment [id=").append(id).append(", content=").append(content).append(", createdAt=")
				.append(createdAt).append(", updatedAt=").append(updatedAt).append(", reviewRating=")
				.append(reviewRating).append(", active=").append(active).append(", user=").append(user).append("]");
		return builder.toString();
	}
	
	
	
	


}
