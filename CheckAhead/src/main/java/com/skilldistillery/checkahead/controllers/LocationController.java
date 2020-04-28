package com.skilldistillery.checkahead.controllers;

import java.util.List;

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
	LocationService locationServ;

	@GetMapping("locations")
	public List<Location> showAllLocations(){
		return null;
	}
	
	@GetMapping("locations/{id}")
	public Location findLocationWithId(@PathVariable Integer id) {
		return null;
	}
	
	@GetMapping("locations/address/{id}")
	public Location findLocationWithAddressId(@PathVariable Integer id) {
		return null;
	}
	
	@GetMapping("locations/name/{name}")
	public Location findLocationWithName(@PathVariable String name) {
		return null;
	}
	
	@PostMapping("locations")
	public Location createLocation(@RequestBody Location location) {
		return null;
	}
	
	@PutMapping("locations/{id}")
	public Location updateLocation(@PathVariable Integer id) {
		return null;
	}
	
	@DeleteMapping("locations/{id}")
	public Location deleteLocation(@PathVariable Integer id) {
		return null;
	}
	
	
}
