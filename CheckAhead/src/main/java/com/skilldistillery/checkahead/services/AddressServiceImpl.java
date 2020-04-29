package com.skilldistillery.checkahead.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.checkahead.entities.Address;
import com.skilldistillery.checkahead.repositories.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	AddressRepository addRepo;

	@Override
	public List<Address> findAllAddresses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Address findAddressById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Address createAddress(Address address) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Address updateAddress(Address address) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteAddress(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
