package com.hung.project1.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

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

import com.hung.project1.entity.FinancePlan;
import com.hung.project1.entity.GeneralPlan;
import com.hung.project1.entity.PersonelPlan;
import com.hung.project1.entity.User;
import com.hung.project1.repository.FinancePlanRepository;
import com.hung.project1.repository.GeneralPlanRepository;
import com.hung.project1.repository.PersonelPlanRepository;
import com.hung.project1.repository.UserRepository;

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
		generalPlan.setStatus("Đã đồng ý");
		planRepo.save(generalPlan);
		
		return "redirect:/proposes/" + id;
	}
	
	@GetMapping("/proposes/{id}/deny")
	public String denyPropose(@PathVariable int id) {
		GeneralPlan generalPlan = planRepo.findById(id);
		generalPlan.setStatus("Đã từ chối");
		planRepo.save(generalPlan);
		
		return "redirect:/proposes/" + id;
	}
	
	@GetMapping("/proposes/add")
	public String viewAddProposeView(Model model) {
		
		int availableId = planRepo.findMaxId() + 1;
		
		List<PersonelPlan> personelPlans= new ArrayList<>() ;
		PersonelPlan emptyPersonelPlan = new PersonelPlan();
		personelPlans.add(emptyPersonelPlan);
		
		List<FinancePlan> financePlans = new ArrayList<>();
		FinancePlan emptyFinancePlan = new FinancePlan();
		financePlans.add(emptyFinancePlan);
		
		GeneralPlan generalPlan = new GeneralPlan();
		generalPlan.setId(availableId);
		generalPlan.setPersonelPlanList(personelPlans);
		generalPlan.setFinancePlanList(financePlans);
		
		model.addAttribute("generalPlan", generalPlan);
		
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
		generalPlan.setStatus("Chưa duyệt");
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
