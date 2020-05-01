package com.skilldistillery.checkahead.services;

import java.util.List;

import com.skilldistillery.checkahead.entities.Address;

public interface AddressService {

	List<Address> findAllAddresses();
	
	Address findAddressById(Integer addressId);

	Address findAddressByUserId(Integer userId);

	Address findAddressByLocationId(Integer locId);
	
	Address createAddress(Address address);

	Address updateAddress(Integer addressId, Address address, String username);
	
	boolean deleteAddress(Integer addressId, String username);
	
	
	
}
