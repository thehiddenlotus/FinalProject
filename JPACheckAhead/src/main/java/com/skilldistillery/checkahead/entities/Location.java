package com.skilldistillery.checkahead.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Location {
	
	// f i e l d s
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="address_id")
	private int addressId;
	
	@Column(name="creator_id")
	private int creatorId;
	
	@Column(name="date_updated")
	private LocalDateTime dateUpdated;
	
	private String name;
	
	@Column(name="date_created")
	private LocalDateTime dateCreated;
	
	private String description;
	
	// m e t h o d s

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public int getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(int creatorId) {
		this.creatorId = creatorId;
	}

	public LocalDateTime getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(LocalDateTime dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Location(int id, int addressId, int creatorId, LocalDateTime dateUpdated, String name,
			LocalDateTime dateCreated, String description) {
		super();
		this.id = id;
		this.addressId = addressId;
		this.creatorId = creatorId;
		this.dateUpdated = dateUpdated;
		this.name = name;
		this.dateCreated = dateCreated;
		this.description = description;
	}

	public Location() {
		super();
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Location [id=").append(id).append(", dateUpdated=").append(dateUpdated).append(", name=")
				.append(name).append(", dateCreated=").append(dateCreated).append(", description=").append(description)
				.append("]");
		return builder.toString();
	}
	
	

}
