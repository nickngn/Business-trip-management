package com.hung.project1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String viewHome() {
		return "homepage";
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
