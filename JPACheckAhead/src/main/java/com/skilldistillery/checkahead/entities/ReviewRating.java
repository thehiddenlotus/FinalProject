package com.skilldistillery.checkahead.entities;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="review_rating")
public class ReviewRating {
	
	// f i e l d s
	
	@EmbeddedId
	private ReviewRatingId id = new ReviewRatingId();
	
	@Column(name="rating_value")
	private int ratingValue;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="review_id")
	@MapsId(value = "reviewId")  
	private Review review;
	
	@OneToOne
	@JoinColumn(name = "rating_id")
	@MapsId(value = "ratingId")  
	private Rating rating;

	// m e t h o d s
	

	public ReviewRating() {
		super();
	}
	
	public ReviewRating(int ratingValue, Review review, Rating rating) {
		super();
		this.ratingValue = ratingValue;
		this.review = review;
		this.rating = rating;
	}

	public ReviewRating(ReviewRatingId id, int ratingValue, Review review, Rating rating) {
		super();
		this.id = id;
		this.ratingValue = ratingValue;
		this.review = review;
		this.rating = rating;
	}

	public ReviewRatingId getId() {
		return id;
	}

	public void setId(ReviewRatingId id) {
		this.id = id;
	}

	public int getRatingValue() {
		return ratingValue;
	}

	public void setRatingValue(int ratingValue) {
		this.ratingValue = ratingValue;
	}

	public Review getReview() {
		return review;
	}

	public void setReview(Review review) {
		this.review = review;
	}

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "ReviewRating [id=" + id + ", ratingValue=" + ratingValue + ", review=" + review + ", rating=" + rating
				+ "]";
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		ReviewRating other = (ReviewRating) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
