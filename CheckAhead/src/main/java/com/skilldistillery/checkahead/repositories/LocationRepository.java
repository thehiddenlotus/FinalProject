package com.skilldistillery.checkahead.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.checkahead.entities.Location;

public interface LocationRepository extends JpaRepository<Location, Integer> {

	List<Location> findByName(String name);

	List<Location> findByAddressId(int id);

}
