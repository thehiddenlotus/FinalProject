package com.skilldistillery.checkahead.services;

import java.util.List;

import com.skilldistillery.checkahead.entities.Address;

public interface AddressService {

	List<Address> findAllAddresses();
	
	Address findAddressById(int id);
	
	Address createAddress(Address address);

	Address updateAddress(Address address);
	
	boolean deleteAddress(int id);
	
	
	
}
