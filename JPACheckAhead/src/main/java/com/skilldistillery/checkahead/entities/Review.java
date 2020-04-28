package com.skilldistillery.checkahead.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Review {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;

@Column(name= "user_id")
private int userId;

@Column(name= "location_id")
private int locationId;

private String content;

private boolean active;

@Column(name= "date_created")
private LocalDateTime dateCreated;

@Column(name= "date_updated")
private LocalDateTime dateUpdated;

@Column(name= "date_visited")
private LocalDateTime dateVistied;

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public int getUserId() {
	return userId;
}

public void setUserId(int userId) {
	this.userId = userId;
}

public int getLocationId() {
	return locationId;
}

public void setLocationId(int locationId) {
	this.locationId = locationId;
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

public Review(int id, int userId, int locationId, String content, boolean active, LocalDateTime dateCreated,
		LocalDateTime dateUpdated, LocalDateTime dateVistied) {
	super();
	this.id = id;
	this.userId = userId;
	this.locationId = locationId;
	this.content = content;
	this.active = active;
	this.dateCreated = dateCreated;
	this.dateUpdated = dateUpdated;
	this.dateVistied = dateVistied;
}

public Review() {
	super();
}


}
