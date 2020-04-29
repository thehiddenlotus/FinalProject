package com.skilldistillery.checkahead.services;

import java.util.List;
import java.util.Optional;

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
		return addRepo.findAll();
	}

	@Override
	public Address findAddressById(Integer addressId) {
		Optional<Address> optAddress = addRepo.findById(addressId);
		if (optAddress.isPresent()) {
			Address foundAddress = optAddress.get();
			return foundAddress;
		}
		return null;
	}

	@Override
	public Address createAddress(Address address) {
		if (address != null) {
			addRepo.saveAndFlush(address);
		} else {
			address = null;
		}
		return address;
	}

	@Override
	public Address updateAddress(Address address) {
		if (addRepo.findById(address.getId()) != null) {
			return addRepo.saveAndFlush(address);
		} else {

			return null;
		}
	}

	@Override
	public boolean deleteAddress(int id) {
		boolean answer = false;
		Optional<Address> address = addRepo.findById(id);
		if (address.isPresent()) {
			addRepo.deleteById(id);
			answer = true;
		}

		return answer;
	}

}
