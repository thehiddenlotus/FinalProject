package com.skilldistillery.checkahead.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.checkahead.entities.Address;
import com.skilldistillery.checkahead.entities.Review;
import com.skilldistillery.checkahead.services.AddressService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4220" }) // Angular local port
public class AddressController {

	@Autowired
	private AddressService addressSvc;
	
	@GetMapping("addresses")
	public List<Address> index(){
		return addressSvc.findAllAddresses();
	}
	
	@GetMapping("addresses/{addressId}")
	public Address show(@PathVariable("addressId") Integer addressId, HttpServletRequest request, HttpServletResponse response) {
		Address address = addressSvc.findAddressById(addressId);
		if (address == null) {
			response.setStatus(404);
		} else {
			response.setStatus(201);
		}
		return address;
	}
	
	@PostMapping("addresses")
    public Address createReview(@RequestBody Address address,HttpServletRequest request, HttpServletResponse response) { 
        try {
           address = addressSvc.createAddress(address);
           if(address == null) {
               response.setStatus(400);
               return null;
           }else {
               response.setStatus(201);
               return address;
           }
       } catch (Exception e) {
           e.printStackTrace();
           response.setStatus(400);
           return null;
       }
        
	}
}
