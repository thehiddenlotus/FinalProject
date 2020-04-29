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
	public Address updateAddress(Integer addressId, Address address) {
		Optional<Address> optAddress = addRepo.findById(addressId);
		Address managedAddress = null;
		if (optAddress.isPresent()) {
			managedAddress = optAddress.get();
			managedAddress.setAddress(address.getAddress());
			managedAddress.setCity(address.getCity());
			managedAddress.setZip(address.getZip());
			managedAddress.setState(address.getState());
			return addRepo.saveAndFlush(managedAddress);
		}
		return null;
	}

	@Override
	public boolean deleteAddress(Integer addressId) {
		boolean answer = false;
		Optional<Address> address = addRepo.findById(addressId);
		if (address.isPresent()) {
			addRepo.deleteById(addressId);
			answer = true;
		}

		return answer;
	}

}
