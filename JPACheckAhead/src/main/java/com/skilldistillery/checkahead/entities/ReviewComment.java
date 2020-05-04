package com.skilldistillery.checkahead.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "review_comment")
public class ReviewComment {
	
	// f i e l d s

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String content;

	@Column(name = "date_created")
	private LocalDateTime createdAt;

	@Column(name = "date_updated")
	private LocalDateTime updatedAt;

	@Column(name = "review_rating")
	private int reviewRating;

	private boolean active;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="review_id")
	private Review review;
	
	// m e t h o d s

	public ReviewComment() {
		super();
	}

	public ReviewComment(int id, String content, LocalDateTime createdAt, LocalDateTime updatedAt, int reviewRating,
			boolean active, User user, Review review) {
		super();
		this.id = id;
		this.content = content;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.reviewRating = reviewRating;
		this.active = active;
		this.user = user;
		this.review = review;
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

	public Review getReview() {
		return review;
	}

	public void setReview(Review review) {
		this.review = review;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ReviewComment [id=").append(id).append(", content=").append(content).append(", createdAt=")
				.append(createdAt).append(", updatedAt=").append(updatedAt).append(", reviewRating=")
				.append(reviewRating).append(", active=").append(active);
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReviewComment other = (ReviewComment) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
}
