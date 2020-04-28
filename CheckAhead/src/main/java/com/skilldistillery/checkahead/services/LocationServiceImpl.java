package com.skilldistillery.checkahead.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.checkahead.entities.Location;
import com.skilldistillery.checkahead.repositories.LocationRepository;

@Service
public class LocationServiceImpl implements LocationService {
	
	@Autowired
	LocationRepository locationRepo;
	
	@Override
	public List<Location> findAllLocations() {
		return locationRepo.findAll();
	}
	
	@Override
	public Location findLocationById(int id) {
		Optional<Location> location = locationRepo.findById(id);
		if(location.isPresent()) {
			return location.get();
		}
		else {
			return null;
		}
	}
	
	@Override
	public List<Location> findLocationByName(String name) {
		List<Location> locations = locationRepo.findByName(name);
		if(locations.size() > 0) {
			return locations;
		}
		else {
			return null;
		}
	}
	
	@Override
	public List<Location> findLocationByAddressId(int id) {
		List<Location> locations = locationRepo.findByAddressId(id);
		if(locations.size() > 0) {
			return locations;
		}
		else {
			return null;
		}
	}
	
	@Override
	public Location createLocation(Location location) {
//		if (locationRepo.findById(location.getId()) != null) {
//			location.setId(0);
//		}
		return locationRepo.saveAndFlush(location);
	}
	
	@Override
	public Location updateLocation(Location location) {
		if (locationRepo.findById(location.getId()) != null) {
			return locationRepo.saveAndFlush(location);
		}
		else {
			return null;			
		}
	}
	
	@Override
	public boolean deleteLocation(int id) {
		boolean answer = false;
		Optional<Location> location = locationRepo.findById(id);
		if (location.isPresent()) {
			locationRepo.deleteById(id);
			answer = true;
		}
		
		return answer;
	}

}
