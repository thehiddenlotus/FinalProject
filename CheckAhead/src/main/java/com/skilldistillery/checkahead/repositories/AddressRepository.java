package com.skilldistillery.checkahead.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.checkahead.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
