package com.skilldistillery.checkahead.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Location {
	
	// f i e l d s
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String description;
	
	@Column(name="google_place_id")
	private String googleId;

	@Column(name="date_updated")
	private LocalDateTime dateUpdated;
	
	@Column(name="date_created")
	private LocalDateTime dateCreated;
	
	@OneToOne
	@JoinColumn(name = "address_id")
	private Address address;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="creator_id")
	private User creator;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "favorites")
	private Set<User> usersFavoritedBy;
	
	@JsonIgnore
	@OneToMany(mappedBy = "location")
	private List<Review> reviews;

	// m e t h o d s

	public Location() {
		super();
	}

	public Location(int id, String name, String description, String googleId, LocalDateTime dateUpdated,
			LocalDateTime dateCreated, Address address, User creator, Set<User> usersFavoritedBy,
			List<Review> reviews) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.googleId = googleId;
		this.dateUpdated = dateUpdated;
		this.dateCreated = dateCreated;
		this.address = address;
		this.creator = creator;
		this.usersFavoritedBy = usersFavoritedBy;
		this.reviews = reviews;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGoogleId() {
		return googleId;
	}

	public void setGoogleId(String googleId) {
		this.googleId = googleId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(LocalDateTime dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public LocalDateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public Set<User> getUsersFavoritedBy() {
		return usersFavoritedBy;
	}

	public void setUsersFavoritedBy(Set<User> usersFavoritedBy) {
		this.usersFavoritedBy = usersFavoritedBy;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	@Override
	public String toString() {
		return "Location [id=" + id + ", name=" + name + ", description=" + description + ", dateUpdated=" + dateUpdated
				+ ", dateCreated=" + dateCreated + ", address=" + address + "]";
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
		Location other = (Location) obj;
		if (id != other.id)
			return false;
		return true;
	}
	

}
