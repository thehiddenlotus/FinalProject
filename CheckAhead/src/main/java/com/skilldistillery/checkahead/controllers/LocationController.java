package com.skilldistillery.checkahead.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.checkahead.entities.Location;
import com.skilldistillery.checkahead.services.LocationService;

@RestController
@RequestMapping ("api")
@CrossOrigin({"*", "http://localhost:4220"}) // Angular local port
public class LocationController {
	
	@Autowired
	private LocationService locationServ;
	
	@GetMapping("locations")
	public List<Location> getAllLocations(HttpServletResponse response){
		List<Location> locations = locationServ.findAllLocations();
		if (locations.size() > 0) {
			return locations;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}
	
	@GetMapping("locations/{id}")
	public Location getLocationWithId(@PathVariable Integer id, HttpServletResponse response){
		Location location = locationServ.findLocationById(id);
		if (location != null) {
			return location;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}
	
	@PostMapping("locations/{userid}")
	public Location createNewLocation(@PathVariable Integer userId, @RequestBody Location location, HttpServletResponse response){
		Location newLocation = locationServ.createLocation(userId, location);
		if (newLocation != null) {
			return location;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}
	
	@PutMapping("locations/{id}")
	public Location updateExistingLocation(@RequestBody Location location, @PathVariable int id, HttpServletResponse response){
		Location editLocation = locationServ.updateLocation(id, location);
		if (editLocation != null) {
			return editLocation;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}
	
	@DeleteMapping("locations/{id}")
	public void deleteLocation(@PathVariable int id, HttpServletResponse response){
		boolean deleted = false;
		try {
			deleted = locationServ.deleteLocation(id);
			if (deleted == true) {
				response.setStatus(204);
			}
		} catch (Exception e) {			
			response.setStatus(404);
		}
	}

}
