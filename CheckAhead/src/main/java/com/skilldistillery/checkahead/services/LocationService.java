package com.skilldistillery.checkahead.services;

import java.util.List;

import com.skilldistillery.checkahead.entities.Location;

public interface LocationService {

	List<Location> findAllLocations();

	Location findLocationById(int id);

	List<Location> findLocationByCreatorId(int id);

	Location createLocation(int userId, Location location, String username);

	Location updateLocation(int id, Location location, String username);

	boolean deleteLocation(int id, String username);

}
