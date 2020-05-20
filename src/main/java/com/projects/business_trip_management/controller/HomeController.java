package com.projects.business_trip_management.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping(value = {"","/"})
	public String viewHome() {
		boolean isAuthenticated = SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
		if (isAuthenticated) {
			return "homepage";
		}
		return "redirect:/login";
	}
	
	@GetMapping("/login")
	public String viewLogin() {
		return "login";
	}
	
	@GetMapping("/logout")
	public String viewAuthenicationException() {
		return "redirect:/login";
	}
}
