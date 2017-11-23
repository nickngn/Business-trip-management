package com.hung.project1.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
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
	private UserDetailsService userDetailServ; 
	
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
	
	@GetMapping("/business-trips/{id}")
	public String viewBusinessTripDetail(@PathVariable int id, ModelMap map) {
		GeneralPlan generalPlan = planRepo.findById(id);
		List<PersonelPlan> personelPlan = personelPlanRepo.findByGeneralPlanId(generalPlan.getId());
		List<FinancePlan> financePlan = financePlanRepo.findByGeneralPlanId(generalPlan.getId());
		
		List<PersonelIncurredPlan> personelIncurredPlans = personelIncurredPlanRepo.findByGeneralPlanId(generalPlan.getId());
		
		List<FinanceIncurredPlan> financeIncurredPlans = financeIncurredPlanRepo.findByGeneralPlanId(generalPlan.getId());
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		
		map.addAttribute("plan", generalPlan);
		map.addAttribute("personelPlan", personelPlan);
		map.addAttribute("financePlan", financePlan);
		map.addAttribute("financeIncurredPlans", financeIncurredPlans);
		map.addAttribute("personelIncurredPlans", personelIncurredPlans);
		
		map.addAttribute("isLeader", username.equals(generalPlan.getLeader().getUsername()));
		//
		return "plan-detail";
	}
	
	@PutMapping("business-trips/{id}/personel-incurred-plans")
	@ResponseBody
	public String viewChangePersonel(@PathVariable("id") int planId, 
			@RequestBody PersonelIncurredPlan personelIncurredPlan ,
			ModelMap map) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		GeneralPlan generalPlan = planRepo.findById(planId);
		String loggedInUsername = auth.getName(),
				leaderUsername = generalPlan.getLeader().getUsername();
		if(!loggedInUsername.equals(leaderUsername)) {
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
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		GeneralPlan generalPlan = planRepo.findById(planId);
		String loggedInUsername = auth.getName(),
				leaderUsername = generalPlan.getLeader().getUsername();
		if(!loggedInUsername.equals(leaderUsername)) {
			return "error";
		}
		FinanceIncurredPlan financeIncurredPlan = new FinanceIncurredPlan();
		financeIncurredPlan.setPlan(generalPlan);
		financeIncurredPlan.setFee(fee);
		financeIncurredPlan.setCost(cost);
		financeIncurredPlan.setConfirmed(false);
		
		financeIncurredPlanRepo.save(financeIncurredPlan);
		
		return "success";
		
	}
	
}
