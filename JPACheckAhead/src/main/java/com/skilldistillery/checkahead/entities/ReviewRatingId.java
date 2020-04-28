package com.skilldistillery.checkahead.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ReviewRatingId implements Serializable {

	// f i e l d s

	private static final long serialVersionUID = 1L;
	
	@Column(name="rating_id")
	private int ratingId;
	
	@Column(name="review_id")
	private int reviewId;

	// m e t h o d s

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ratingId;
		result = prime * result + reviewId;
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
		ReviewRatingId other = (ReviewRatingId) obj;
		if (ratingId != other.ratingId)
			return false;
		if (reviewId != other.reviewId)
			return false;
		return true;
	}

	public ReviewRatingId(int ratingId, int reviewId) {
		super();
		this.ratingId = ratingId;
		this.reviewId = reviewId;
	}

	public ReviewRatingId() {
		super();
	}

	public int getRatingId() {
		return ratingId;
	}

	public void setRatingId(int ratingId) {
		this.ratingId = ratingId;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ReviewRatingId [ratingId=").append(ratingId).append(", reviewId=").append(reviewId).append("]");
		return builder.toString();
	}
	
	
	
	
	
}
