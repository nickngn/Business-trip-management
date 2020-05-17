package com.projects.business_trip_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.projects.business_trip_management.entity.GeneralPlan;
import com.projects.business_trip_management.repository.FinanceIncurredPlanRepository;
import com.projects.business_trip_management.repository.FinancePlanRepository;
import com.projects.business_trip_management.repository.GeneralPlanRepository;
import com.projects.business_trip_management.repository.PersonelIncurredPlanRepository;
import com.projects.business_trip_management.repository.PersonelPlanRepository;
import com.projects.business_trip_management.service.PaymentService;

@Controller
public class HistoryController {
	
	@Autowired
	private GeneralPlanRepository planRepo;

	@Autowired
	private PersonelPlanRepository personelPlanRepo;

	@Autowired
	private FinancePlanRepository financePlanRepo;

	@Autowired
	private PersonelIncurredPlanRepository personelIncurredPlanRepo;

	@Autowired
	private FinanceIncurredPlanRepository financeIncurredPlanRepo;

	@Autowired
	private PaymentService paymentService;

	@GetMapping("/histories")
	public String viewHistory(
			@RequestParam(name="page" ,required=false, defaultValue="1") int page, 
			@RequestParam(name="size", required=false, defaultValue="10") int size, 
			ModelMap map) {

		Pageable pageRequest = new PageRequest(page-1, size);
		Page<GeneralPlan> acceptedPlans = planRepo.findConfirmedOrFinishedPlans(pageRequest);
		
		map.addAttribute("plans", acceptedPlans);
		map.addAttribute("page", page);
		map.addAttribute("size", size);
		//
		map.addAttribute("title", "Lịch sử");
		map.addAttribute("removeCreate", true);
		map.addAttribute("basePath", "/business-trips");
		
		return "plans";
	}
}
