package com.hung.project1.api;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hung.project1.api.dto.FinancePlanDTO;
import com.hung.project1.api.dto.GeneralPlanDTO;
import com.hung.project1.api.dto.PersonelPlanDTO;
import com.hung.project1.entity.FinancePlan;
import com.hung.project1.entity.GeneralPlan;
import com.hung.project1.entity.PersonelPlan;
import com.hung.project1.repository.FinancePlanRepository;
import com.hung.project1.repository.GeneralPlanRepository;
import com.hung.project1.repository.PersonelPlanRepository;

@RestController
@RequestMapping("/api/plans")
public class GeneralPlanAPI {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	
	@Autowired
	private GeneralPlanRepository generalPlanRepo;
	
	@Autowired
	private PersonelPlanRepository personelPlanRepo;
	
	@Autowired
	private FinancePlanRepository financePlanRepo;
	
	@GetMapping()
	public HttpEntity<Page<GeneralPlanDTO>> getAllPlans(
			@RequestParam(required=false, defaultValue = "1") int page,
			@RequestParam(required=false, defaultValue = "10") int size) {
		Pageable pageRequest = new PageRequest(page, size);    //process page request 
		Page<GeneralPlan> generalPlanPage = generalPlanRepo.findAll(pageRequest);
		List<GeneralPlanDTO> generalPlanDTOs = new ArrayList<>();
		generalPlanPage.getContent().forEach(plan -> {
			GeneralPlanDTO planDTO = new GeneralPlanDTO();
			BeanUtils.copyProperties(plan, planDTO);
			
			Link linkToSelf = linkTo(GeneralPlanAPI.class)
								.slash(plan.getId())
								.withSelfRel();
			Link linkToLeader = linkTo(UserAPI.class)
								.slash(plan.getLeader().getId())
								.withRel("leader");
			planDTO.add(linkToSelf);
			planDTO.add(linkToLeader);
			generalPlanDTOs.add(planDTO);
		});
		Page<GeneralPlanDTO> generalPlanDTOPage = new PageImpl<>(
				generalPlanDTOs, pageRequest, generalPlanPage.getTotalElements());
		
		return new HttpEntity<Page<GeneralPlanDTO>>(generalPlanDTOPage);
	} 
	
	@GetMapping("/{planId}")
	public HttpEntity<GeneralPlanDTO> getPlan(@PathVariable(name="planId") int planId) {
		GeneralPlan plan = generalPlanRepo.findById(planId);
		GeneralPlanDTO generalPlanDTO = new GeneralPlanDTO();
		BeanUtils.copyProperties(plan, generalPlanDTO);
		Link linkToLeader = linkTo( methodOn(UserAPI.class)
								.getUserDetail(plan.getLeader().getId()))
								.withRel("leader");
		Link linkToPersonelPlan = linkTo( methodOn (GeneralPlanAPI.class)
				.getPersonelPlanByGeneralPlanId(planId))
				.withRel("personelPlan");
//		Link linkToPersonelIncurredPlan = 
		Link linkToFinancePlan = linkTo( methodOn (GeneralPlanAPI.class)
				.getFinancePlanByGeneralPlanId(planId))
				.withRel("financePlan");
//		Link linkToFinanceIncurredPlan = 
		
		generalPlanDTO.add(linkToLeader);
		generalPlanDTO.add(linkToPersonelPlan);
		generalPlanDTO.add(linkToFinancePlan);
		
		return new HttpEntity<GeneralPlanDTO>(generalPlanDTO);
	}
	
	@GetMapping("/unconfirmed-plans")
	public HttpEntity<List<GeneralPlanDTO>> getUnconfirmedPlan() {
		Pageable pageRequest = new PageRequest(1, 10);
		Page<GeneralPlan> allPlan =  generalPlanRepo.findUnconfirmedPlan(pageRequest);
		List<GeneralPlanDTO> allPlanDTO = new ArrayList<>();
		allPlan.forEach(plan -> {
			GeneralPlanDTO planDTO = new GeneralPlanDTO();
			BeanUtils.copyProperties(plan, planDTO);
			
			Link linkToSelf = linkTo(GeneralPlanAPI.class)
								.slash(plan.getId())
								.withSelfRel();
			Link linkToLeader = linkTo(UserAPI.class)
								.slash(plan.getLeader().getId())
								.withRel("leader");
			planDTO.add(linkToSelf);
			planDTO.add(linkToLeader);
			allPlanDTO.add(planDTO);
		});
				
		return new HttpEntity<List<GeneralPlanDTO>>(allPlanDTO);
	}
	
	
	@GetMapping("/{planId}/personel-plan")
	public HttpEntity<List<PersonelPlanDTO>> getPersonelPlanByGeneralPlanId(
			@PathVariable("planId") int planId) {
		List<PersonelPlan> personels = personelPlanRepo.findByGeneralPlanId(planId);
		List<PersonelPlanDTO> personelDTOs = new ArrayList<>();
		personels.forEach(personel -> {
			PersonelPlanDTO personelPlanDTO = new PersonelPlanDTO();
			personelPlanDTO.setGeneralPlanId(personel.getUser().getId());
			personelPlanDTO.setUserId(personel.getPlan().getId());
			Link linkToUser = linkTo(methodOn(UserAPI.class)
					.getUserDetail(personel.getId()))
					.withRel("personel");
			personelPlanDTO.add(linkToUser);
			personelDTOs.add(personelPlanDTO);
		});
		
		return new HttpEntity<List<PersonelPlanDTO>>(personelDTOs);
	}
	
	
	@GetMapping("/{planId}/finance-plan")
	public HttpEntity<List<FinancePlanDTO>> getFinancePlanByGeneralPlanId(
			@PathVariable("planId") int planId) {
		List<FinancePlan> finances = financePlanRepo.findByGeneralPlanId(planId);
		List<FinancePlanDTO> financeDTOs = new ArrayList<>();
		finances.forEach(finance -> {
			FinancePlanDTO financePlanDTO = new FinancePlanDTO();
			BeanUtils.copyProperties(finance, financePlanDTO);
			financePlanDTO.setGeneralPlanId(planId);
			financeDTOs.add(financePlanDTO);
		});
		
		return new HttpEntity<List<FinancePlanDTO>>(financeDTOs);
	}
	
}
