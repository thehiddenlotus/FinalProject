package com.skilldistillery.checkahead.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.checkahead.entities.Address;
import com.skilldistillery.checkahead.entities.Location;
import com.skilldistillery.checkahead.entities.User;
import com.skilldistillery.checkahead.repositories.AddressRepository;
import com.skilldistillery.checkahead.repositories.LocationRepository;
import com.skilldistillery.checkahead.repositories.UserRepository;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addRepo;
	
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private LocationRepository locRepo;

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
	public Address updateAddress(Integer addressId, Address address, String username) {
		Optional<Address> optAddress = addRepo.findById(addressId);
		Address managedAddress = null;
		if (optAddress.isPresent()) {
			managedAddress = optAddress.get();
			managedAddress.setAddress(address.getAddress());
			managedAddress.setCity(address.getCity());
			managedAddress.setZip(address.getZip());
			managedAddress.setState(address.getState());
			if (userRepo.findByUsername(username).getAddress().getId() == managedAddress.getId()) {
				return addRepo.saveAndFlush(managedAddress);
			}
		}
		return null;
	}

	@Override
	public boolean deleteAddress(Integer addressId, String username) {
		boolean answer = false;
		Optional<Address> address = addRepo.findById(addressId);
		if (address.isPresent() && (
				userRepo.findByUsername(username).getAddress().getId() == addressId 
				|| userRepo.findByUsername(username).getRole().equals("admin"))
			) {
			addRepo.deleteById(addressId);
			answer = true;
		}

		return answer;
	}

	@Override
	public Address findAddressByUserId(Integer userId) {
		Optional<User> user = userRepo.findById(userId);
		if (user.isPresent()) {
			Address foundAddress = user.get().getAddress();
			return foundAddress;
		}
		return null;
	}

	@Override
	public Address findAddressByLocationId(Integer locId) {
		Optional<Location> opt = locRepo.findById(locId);
		if (opt.isPresent()) {
			Address foundAddress = opt.get().getAddress();
			return foundAddress;
		}
		return null;
	}

}
