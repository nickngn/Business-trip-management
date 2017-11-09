package com.hung.project1.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.hung.project1.entity.PersonelPlan;
import com.hung.project1.entity.Plan;
import com.hung.project1.entity.User;
import com.hung.project1.repository.FinancePlanRepository;
import com.hung.project1.repository.PersonelPlanRepository;
import com.hung.project1.repository.PlanRepository;
import com.hung.project1.repository.UserRepository;

@RestController
public class TestEntityRestController {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	UserRepository userRepo; 
	
	@Autowired
	PlanRepository planRepo; 
	
	
	@Autowired
	private PersonelPlanRepository personelPlanRepo;
	
	@Autowired
	private FinancePlanRepository financePlanRepo;


	@GetMapping("/users")
	public String testUserRepository() {
		return userRepo.findAll().toString();
	}
	
	@GetMapping("/proposes-test")
	public Page viewPlanProposes(
			@PathVariable(required=false) Optional<Integer> page, 
			@PathVariable(required=false) Optional<Integer> size, 
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
			_size = 20;
		} else {
			_size = size.get();
		}
		
		Pageable pageRequest = new PageRequest(_page, _size);
		Page<Plan> notConfirmedPlans = planRepo.findNotConfirmedPlan(pageRequest);
		map.addAttribute("notConfirmedPlans", notConfirmedPlans.getContent());
		//
		return notConfirmedPlans;
	}
	
	@GetMapping("/plan/{id}")
	public Plan viewPlanProposeDetail(@PathVariable int id) {
		Plan plan = planRepo.findOne(id);
		//
		return plan;
	}
	
	@GetMapping("/personelplan/{id}")
	public Object viewPersonelPlanProposeDetail(@PathVariable int id, ModelMap map) {
		List<PersonelPlan> personelPlan = personelPlanRepo.findByPlanId(id);
		//
		return personelPlan;
	}
	
	
	@GetMapping("/employees")
	public List<User> viewEmployees() {
		
		//
		return userRepo.findAllEmployee();
	}
}
