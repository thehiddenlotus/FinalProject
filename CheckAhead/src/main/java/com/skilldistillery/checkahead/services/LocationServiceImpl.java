package com.skilldistillery.checkahead.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.checkahead.repositories.LocationRepository;

@Service
public class LocationServiceImpl implements LocationService {
	
	@Autowired
	LocationRepository locationRepo;

}
