package com.skilldistillery.checkahead.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RefreshController {
	
	@GetMapping({
		"home",
		"search",
		"team",
		"settings",
		"register",
		"login",
		"changeAddress"
	})
	
	public String home() {
		return "index.html";
	}
	
}