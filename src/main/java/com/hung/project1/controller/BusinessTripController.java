package com.hung.project1.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hung.project1.entity.FinanceIncurredPlan;
import com.hung.project1.entity.FinancePlan;
import com.hung.project1.entity.GeneralPlan;
import com.hung.project1.entity.PersonelIncurredPlan;
import com.hung.project1.entity.PersonelPlan;
import com.hung.project1.entity.User;
import com.hung.project1.repository.FinanceIncurredPlanRepository;
import com.hung.project1.repository.FinancePlanRepository;
import com.hung.project1.repository.GeneralPlanRepository;
import com.hung.project1.repository.PersonelIncurredPlanRepository;
import com.hung.project1.repository.PersonelPlanRepository;
import com.hung.project1.repository.UserRepository;
import com.hung.project1.service.RoleService;

@Controller
public class BusinessTripController {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
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
	private RoleService roleService;
	
	@Autowired
	private UserRepository userRepo;
	

	@GetMapping("/business-trips")
	public String viewBusinessTrips(
			@RequestParam(name="page" ,required=false, defaultValue="1") int page, 
			@RequestParam(name="size", required=false, defaultValue="10") int size, 
			ModelMap map) {

		Pageable pageRequest = new PageRequest(page-1, size);
		Page<GeneralPlan> acceptedPlans = planRepo.findAcceptedPlan(pageRequest);
		
		map.addAttribute("plans", acceptedPlans);
		map.addAttribute("page", page);
		map.addAttribute("size", size);
		//
		map.addAttribute("title", "Các chuyến công tác đang trong quá trình");
		map.addAttribute("removeCreate", true);
		map.addAttribute("basePath", "/business-trips");
		
		return "plans";
	}
	
	@GetMapping("/business-trips/{planId}")
	public String viewBusinessTripDetail(@PathVariable int planId, ModelMap map) {
		GeneralPlan generalPlan = planRepo.findById(planId);
		
		List<PersonelPlan> personelPlanList = personelPlanRepo
				.findByGeneralPlanId(generalPlan.getId());
		
		List<FinancePlan> financePlanList = financePlanRepo
				.findByGeneralPlanId(generalPlan.getId());
		
		List<PersonelIncurredPlan> personelIncurredPlanList = personelIncurredPlanRepo
				.findByGeneralPlanId(generalPlan.getId());
		
		List<FinanceIncurredPlan> financeIncurredPlanList = financeIncurredPlanRepo
				.findByGeneralPlanId(generalPlan.getId());
		
		map.addAttribute("plan", generalPlan);
		map.addAttribute("personelPlan", personelPlanList);
		map.addAttribute("financePlan", financePlanList);
		map.addAttribute("financeIncurredPlans", financeIncurredPlanList);
		map.addAttribute("personelIncurredPlans", personelIncurredPlanList);
		
		map.addAttribute("isLeader", roleService.checkLoggedInUserAsLeader(generalPlan));
		//
		return "plan-detail";
	}
	
	@PutMapping("business-trips/{id}/personel-incurred-plans")
	@ResponseBody
	public String viewChangePersonel(@PathVariable("id") int planId, 
			@RequestBody PersonelIncurredPlan personelIncurredPlan ,
			ModelMap map) {
		GeneralPlan generalPlan = planRepo.findById(planId);
		
		if(!roleService.checkLoggedInUserAsLeader(generalPlan)) {
			return "Permission Error";
		}
		User user = userRepo.findByUsername(personelIncurredPlan.getUser().getUsername());
		personelIncurredPlan.setPlan(generalPlan);
		personelIncurredPlan.setUser(user);
		personelIncurredPlanRepo.save(personelIncurredPlan);
		
		return "Success";
	}
	
	@PutMapping("business-trips/{id}/finance-incurred-plans")
	@ResponseBody
	public String viewChangeFinance(@PathVariable("id") int planId, 
			@RequestParam("fee") String fee,
			@RequestParam("cost") double cost,
			ModelMap map) {
		GeneralPlan generalPlan = planRepo.findById(planId);
		if(!roleService.checkLoggedInUserAsLeader(generalPlan)) {
			return "Permission error";
		}
		//create finance incurred plan
		FinanceIncurredPlan financeIncurredPlan = new FinanceIncurredPlan();
		financeIncurredPlan.setPlan(generalPlan);
		financeIncurredPlan.setFee(fee);
		financeIncurredPlan.setCost(cost);
		financeIncurredPlan.setConfirmed(false);
		//save
		financeIncurredPlanRepo.save(financeIncurredPlan);
		
		return "success";
	}
	
}
