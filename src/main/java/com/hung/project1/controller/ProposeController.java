package com.hung.project1.controller;

import java.util.List;
import java.util.Optional;

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

import com.hung.project1.entity.FinancePlan;
import com.hung.project1.entity.PersonelPlan;
import com.hung.project1.entity.Plan;
import com.hung.project1.repository.FinancePlanRepository;
import com.hung.project1.repository.PersonelPlanRepository;
import com.hung.project1.repository.PlanRepository;

@Controller
public class ProposeController {
	Logger logger = LoggerFactory.getLogger(ProposeController.class);
	
	@Autowired
	private PlanRepository planRepo; 
	
	@Autowired
	private PersonelPlanRepository personelPlanRepo;
	
	@Autowired
	private FinancePlanRepository financePlanRepo;

	@GetMapping("/proposes")
	public String viewPlanProposes(
			@PathVariable(name="page" ,required=false) Optional<Integer> page, 
			@PathVariable(name="size", required=false) Optional<Integer> size, 
			ModelMap map) {
		logger.info(" - viewPlanProposes() :");
		int _page, _size;
		
		//process page value
		if (!page.isPresent()) {
			_page = 1;
		} else {
			_page = page.get();
		}
		
		//process size value
		if (!size.isPresent()) {
			_size = 10;
		} else {
			_size = size.get();
		}
		System.out.println("page: " + _page);
		System.out.println("size: " + _size);
		System.out.println(page);
		Pageable pageRequest = new PageRequest(_page, _size);
		Page<Plan> notConfirmedPlans = planRepo.findNotConfirmedPlan(pageRequest);
		map.addAttribute("notConfirmedPlans", notConfirmedPlans);
		//
		notConfirmedPlans.forEach(System.out::println);
		return "proposes";
	}
	
	@GetMapping("/proposes/{id}")
	public String viewPlanProposeDetail(@PathVariable int id, ModelMap map) {
		Plan plan = planRepo.findOne(id);
		List<PersonelPlan> personelPlan = personelPlanRepo.findByPlanId(plan.getId());
		List<FinancePlan> financePlan = financePlanRepo.findByPlanId(plan.getId());
		
		map.addAttribute("plan", plan);
		map.addAttribute("personelPlan", personelPlan);
		map.addAttribute("financePlan", financePlan);
		
		//
		return "propose-detail";
	}
	
	@GetMapping("/proposes/add")
	public String viewAddProposeView() {
		
		return "propose-detail";
	}
}
