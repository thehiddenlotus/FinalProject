package com.skilldistillery.checkahead.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
	private List<Location> getAllLocations(HttpServletResponse response){
		List<Location> locations = locationServ.findAllLocations();
		if (locations.size() > 0) {
			return locations;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}
}
