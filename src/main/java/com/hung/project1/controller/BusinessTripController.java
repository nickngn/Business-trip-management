package com.hung.project1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BusinessTripController {

	@GetMapping("/business-trips")
	public String viewBusinessTrips() {
		return "business-trips";
	}
	
	@GetMapping("/business-trips/{id}")
	public String viewBusinessTripDetail() {
		return "business-trip-detail";
	}
	
	@GetMapping("/business-trips/{id}/personel-proposes/{propose-id}")
	public String viewPersonelProposeDetail() {
		return "personel-propose";
	}
	
	@GetMapping("/business-trips/{id}/fee-proposes/{propose-id}")
	public String viewFeeProposeDetail() {
		return "fee-propose";
	}
}
