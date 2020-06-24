package com.projects.business_trip_management.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.projects.business_trip_management.repository.FinancePlanRepository;
import com.projects.business_trip_management.repository.GeneralPlanRepository;
import com.projects.business_trip_management.repository.PersonelPlanRepository;
import com.projects.business_trip_management.repository.ThresholdRepository;
import com.projects.business_trip_management.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.projects.business_trip_management.entity.FinancePlan;
import com.projects.business_trip_management.entity.GeneralPlan;
import com.projects.business_trip_management.entity.PersonelPlan;
import com.projects.business_trip_management.entity.Threshold;
import com.projects.business_trip_management.entity.User;

@Controller
public class ProposeController {
	Logger logger = LoggerFactory.getLogger(ProposeController.class);
	
	@Autowired
	private GeneralPlanRepository planRepo;
	
	@Autowired
	private PersonelPlanRepository personelPlanRepo;
	
	@Autowired
	private FinancePlanRepository financePlanRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ThresholdRepository thresholdRepo;

	@GetMapping("/proposes")
	public String viewPlanProposes(
			@RequestParam(name="page" ,required=false, defaultValue="1") int page, 
			@RequestParam(name="size", required=false, defaultValue="10") int size, 
			ModelMap map) {
		logger.info(" - viewPlanProposes() :");

		Pageable pageRequest = new PageRequest(page-1, size);
		Page<GeneralPlan> unconfirmedPlans = planRepo.findUnconfirmedPlan(pageRequest);
		map.addAttribute("plans", unconfirmedPlans);
		map.addAttribute("page", page);
		map.addAttribute("size", size);
		map.addAttribute("basePath", "/proposes");
		//
		map.addAttribute("title", "Đề xuất công tác");
		
		return "plans";
	}
	
	@GetMapping("/proposes/{id}")
	public String viewPlanProposeDetail(@PathVariable int id, ModelMap map) {
		GeneralPlan generalPlan = planRepo.findById(id);
		List<PersonelPlan> personelPlan = personelPlanRepo.findByGeneralPlanId(generalPlan.getId());
		List<FinancePlan> financePlan = financePlanRepo.findByGeneralPlanId(generalPlan.getId());
		
		map.addAttribute("plan", generalPlan);
		map.addAttribute("personelPlan", personelPlan);
		map.addAttribute("financePlan", financePlan);
		
		//
		return "plan-detail";
	}
	
	@GetMapping("/proposes/{id}/accept")
	public String acceptPropose(@PathVariable int id) {
		GeneralPlan generalPlan = planRepo.findById(id);
		generalPlan.setStatus("On_Going");
		planRepo.save(generalPlan);
		
		return "redirect:/proposes/" + id;
	}
	
	@GetMapping("/proposes/{id}/deny")
	public String denyPropose(@PathVariable int id) {
		GeneralPlan generalPlan = planRepo.findById(id);
		generalPlan.setStatus("Denied");
		planRepo.save(generalPlan);
		
		return "redirect:/proposes/" + id;
	}
	
	@GetMapping("/proposes/add")
	public String viewAddProposeView(Model model) {
		
		int availableId = planRepo.findMaxId() + 1;
		
		List<Threshold> thresholds = this.thresholdRepo.findAll();
		
		List<PersonelPlan> personelPlans= new ArrayList<>() ;
		PersonelPlan emptyPersonelPlan = new PersonelPlan();
		personelPlans.add(emptyPersonelPlan);
		
		List<FinancePlan> financePlans = new ArrayList<>();
		thresholds.forEach(th -> financePlans.add(new FinancePlan(th.getFee(), th.getAmount(), true)));
		
		GeneralPlan generalPlan = new GeneralPlan();
		generalPlan.setId(availableId);
		generalPlan.setPersonelPlanList(personelPlans);
		generalPlan.setFinancePlanList(financePlans);
		
		model.addAttribute("generalPlan", generalPlan);
		model.addAttribute("thresholds", this.thresholdRepo.findAll());
		
		return "add-propose";
	}
	
	@PostMapping("/proposes/add")
	public String proposeGeneralPlan(@Valid @ModelAttribute GeneralPlan generalPlan, 
			BindingResult result) {
		logger.info("proposeGeneralPlan()");
		
		if (result.hasErrors()) {
			return "add-propose";
		}
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User loggedUser = userRepo.findByUsername(username);
		
		generalPlan.setLeader(loggedUser);
		generalPlan.setStatus("Unconfirmed");
		planRepo.save(generalPlan);
		
		generalPlan.getPersonelPlanList().forEach(personelPlan -> {
			String nameOfChoseUser = personelPlan.getUser().getUsername();
			User userFoundByUsername = userRepo.findByUsername(nameOfChoseUser);
			if (userFoundByUsername != null) {
				personelPlan.setPlan(generalPlan);
				personelPlan.setUser(userFoundByUsername);
				personelPlanRepo.save(personelPlan);
			}
		});
		
		generalPlan.getFinancePlanList().forEach(financePlan -> {
			financePlan.setGeneralPlan(generalPlan);
			financePlanRepo.save(financePlan);
		});
		
		return "redirect:/proposes";
	}
}
