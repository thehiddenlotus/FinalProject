package com.skilldistillery.checkahead.services;

import java.util.List;

import com.skilldistillery.checkahead.entities.Location;

public interface LocationService {

	List<Location> findAllLocations();

	Location findLocationById(int id);

//	List<Location> findLocationByName(String userName);
//
//	List<Location> findLocationByAddressId(int id);

	Location createLocation(int userId, Location location);

	Location updateLocation(int id, Location location);

	boolean deleteLocation(int id);

}
