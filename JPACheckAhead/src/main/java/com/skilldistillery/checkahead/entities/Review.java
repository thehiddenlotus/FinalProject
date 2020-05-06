package com.skilldistillery.checkahead.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Review {
	
	// f i e l d s

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String content;

	private boolean active;

	@Column(name = "date_created")
	@CreationTimestamp
	private LocalDateTime dateCreated;

	@Column(name = "date_updated")
	@CreationTimestamp
	private LocalDateTime dateUpdated;

	@Column(name = "date_visited")
	@CreationTimestamp
	private LocalDateTime dateVistied;
	
	
	@ManyToOne
	@JoinColumn(name="location_id")
	private Location location;

	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@JsonIgnore
	@OneToMany(mappedBy = "review")
	private List<ReviewRating> ratings;


	@OneToMany(mappedBy = "review")
	private List<ReviewComment> comments;
	
	// m e t h o d s
	
	public Review() {
		super();
	}

	public Review(int id, String content, boolean active, LocalDateTime dateCreated, LocalDateTime dateUpdated,
			LocalDateTime dateVistied, Location location, User user, List<ReviewRating> ratings,
			List<ReviewComment> comments) {
		super();
		this.id = id;
		this.content = content;
		this.active = active;
		this.dateCreated = dateCreated;
		this.dateUpdated = dateUpdated;
		this.dateVistied = dateVistied;
		this.location = location;
		this.user = user;
		this.ratings = ratings;
		this.comments = comments;
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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public LocalDateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	public LocalDateTime getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(LocalDateTime dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public LocalDateTime getDateVistied() {
		return dateVistied;
	}

	public void setDateVistied(LocalDateTime dateVistied) {
		this.dateVistied = dateVistied;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<ReviewRating> getRatings() {
		return ratings;
	}

	public void setRatings(List<ReviewRating> ratings) {
		this.ratings = ratings;
	}

	public List<ReviewComment> getComments() {
		return comments;
	}

	public void setComments(List<ReviewComment> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", content=" + content + ", active=" + active + ", dateCreated=" + dateCreated
				+ ", dateUpdated=" + dateUpdated + ", dateVistied=" + dateVistied + ", location=" + location + ", user="
				+ user + "]";
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
		Review other = (Review) obj;
		if (id != other.id)
			return false;
		return true;
	}


}
