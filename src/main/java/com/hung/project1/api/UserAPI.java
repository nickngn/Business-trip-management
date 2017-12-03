package com.hung.project1.api;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

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

import com.hung.project1.api.dto.GeneralPlanDTO;
import com.hung.project1.api.dto.UserDTO;
import com.hung.project1.entity.GeneralPlan;
import com.hung.project1.entity.PersonelIncurredPlan;
import com.hung.project1.entity.PersonelPlan;
import com.hung.project1.entity.User;
import com.hung.project1.repository.GeneralPlanRepository;
import com.hung.project1.repository.PersonelIncurredPlanRepository;
import com.hung.project1.repository.PersonelPlanRepository;
import com.hung.project1.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserAPI {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private GeneralPlanRepository generalPlanRepo;
	
	@Autowired
	private PersonelPlanRepository personelPlanRepo; 
	
	@Autowired
	private PersonelIncurredPlanRepository personelIncurredPlanRepo; 
	
	@GetMapping(value= {"/", ""})
	public HttpEntity<Page<UserDTO>> getUsers(
			@RequestParam(name="page", defaultValue="1", required = false) int page, 
			@RequestParam(name="size", defaultValue="10", required = false) int size) {
		//filter page request
		Pageable pageRequest = new PageRequest(page - 1, size);
		
		//query
		Page<User> users = userRepo.findAll(pageRequest);
		
		//convert to dto
		List<UserDTO> userDTOs = new ArrayList<>();
		users.getContent().forEach(user -> {
			UserDTO userDTO = new UserDTO();
			BeanUtils.copyProperties(user, userDTO);
			Link link = linkTo(UserAPI.class)
							.slash(user.getId())
							.withSelfRel();
			userDTO.add(link);    //add self link
			userDTOs.add(userDTO);
		});
		Page<UserDTO> userDTOPage = new PageImpl<>(userDTOs, pageRequest, users.getTotalElements());
		
		return new HttpEntity<Page<UserDTO>>(userDTOPage);
	}

	@GetMapping("/{userId}")
	public HttpEntity<UserDTO> getUserDetail(@PathVariable(name="userId") int userId) {
		User user = userRepo.findById(userId);
		UserDTO userDTO = new UserDTO();
		BeanUtils.copyProperties(user, userDTO);
		
		if (user.getPlans().size() > 0) {
			userDTO.add(linkTo( methodOn(UserAPI.class)
					.getPlansLeadedByUser(userId, 1, 5)).withRel("leadedPlans"));
		}
		
		if (personelPlanRepo.findByUserId(userId).size() > 0) {
			userDTO.add(linkTo( methodOn(UserAPI.class)
					.getAttendedGeneralPlan(userId)).withRel("memberedPlans"));
		}
		
		return new HttpEntity<UserDTO>(userDTO);
	}
	
	@GetMapping("/{userId}/membered-plans")
	public HttpEntity<List<GeneralPlanDTO>> getAttendedGeneralPlan(
			@PathVariable("userId") int userId) {
		List<GeneralPlanDTO> generalPlanDTOs = new ArrayList<>();
		List<PersonelPlan> personelPlans = personelPlanRepo.findByUserId(userId);
		List<PersonelIncurredPlan> personelIncurredPlans = 
				personelIncurredPlanRepo.findByAttendedUser(userId);
		
		personelPlans.forEach(personel -> {
			GeneralPlan generalPlan = generalPlanRepo.findById(personel.getPlan().getId());
			GeneralPlanDTO generalPlanDTO = new GeneralPlanDTO();
			BeanUtils.copyProperties(generalPlan, generalPlanDTO);
			
			Link linkToSelf = linkTo(GeneralPlanAPI.class)
					.slash(generalPlan.getId())
					.withSelfRel();
			Link linkToLeader = linkTo(UserAPI.class)
								.slash(generalPlan.getLeader().getId())
								.withRel("leader");
			generalPlanDTO.add(linkToSelf);
			generalPlanDTO.add(linkToLeader);
			generalPlanDTOs.add(generalPlanDTO);
		});
		
		personelIncurredPlans.forEach(personel -> {
			GeneralPlan generalPlan = generalPlanRepo.findById(personel.getPlan().getId());
			GeneralPlanDTO generalPlanDTO = new GeneralPlanDTO();
			BeanUtils.copyProperties(generalPlan, generalPlanDTO);
			
			Link linkToSelf = linkTo(GeneralPlanAPI.class)
					.slash(generalPlan.getId())
					.withSelfRel();
			Link linkToLeader = linkTo(UserAPI.class)
								.slash(generalPlan.getLeader().getId())
								.withRel("leader");
			generalPlanDTO.add(linkToSelf);
			generalPlanDTO.add(linkToLeader);
			generalPlanDTOs.add(generalPlanDTO);
		});
		
		
		return new HttpEntity<List<GeneralPlanDTO>>(generalPlanDTOs);
	}
	
	@GetMapping("/{userId}/leaded-plans")
	public HttpEntity<Page<GeneralPlanDTO>> getPlansLeadedByUser(
			@PathVariable(name="userId") int userId,
			@RequestParam(required=false, defaultValue = "1", name="page") int page,
			@RequestParam(required=false, defaultValue = "5", name="size") int size) {
		
		Pageable pageRequest = new PageRequest(page - 1 , size);
		
		Page<GeneralPlan> plans = generalPlanRepo.findByLeaderId(userId, pageRequest);
		List<GeneralPlanDTO> generalPlanDTOs = new ArrayList<>();
		plans.getContent().forEach(plan -> {
			GeneralPlanDTO generalPlanDTO = new GeneralPlanDTO();
			BeanUtils.copyProperties(plan, generalPlanDTO);
			Link link = linkTo(  GeneralPlanAPI.class )
                    		.slash(plan.getId())
                    		.withSelfRel();
			generalPlanDTO.add(link);
			generalPlanDTOs.add(generalPlanDTO);
		});
		Page<GeneralPlanDTO> generalPlanDTOsPage = new PageImpl<>(
				generalPlanDTOs, pageRequest, plans.getTotalElements());
		
		return new HttpEntity<Page<GeneralPlanDTO>>(generalPlanDTOsPage);
	}
	
	@GetMapping("/suggested-users/{username}")
	public HttpEntity<Page<UserDTO>> suggestUserByUsername(@PathVariable String username){
		Pageable pageRequest = new PageRequest(0,7);
		Page<User> suggestedUser = userRepo.findByUsernameContaining(username, pageRequest);
		List<UserDTO> suggestedUserDTO = new ArrayList<>();
		suggestedUser.forEach(user -> {
			UserDTO userDTO = new UserDTO();
			BeanUtils.copyProperties(user, userDTO);
			Link link = linkTo( methodOn(UserAPI.class).getUserDetail(user.getId())).withSelfRel();
			userDTO.add(link);
			suggestedUserDTO.add(userDTO);
		});
		Page<UserDTO> suggesteduserDTOPage = new PageImpl<>(
				suggestedUserDTO, pageRequest, suggestedUser.getTotalElements());
		
		return new HttpEntity<Page<UserDTO>>(suggesteduserDTOPage);
	};
}
