package com.skilldistillery.checkahead.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.checkahead.entities.Location;

public interface LocationRepository extends JpaRepository<Location, Integer> {

}
