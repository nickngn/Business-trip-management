package com.hung.project1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.hung.project1.entity.GeneralPlan;
import com.hung.project1.repository.GeneralPlanRepository;

@Service
public class RoleService {
	
	@Autowired
	GeneralPlanRepository planRepo;
	
	public boolean isLeaderUsername(int planId) {
		GeneralPlan generalPlan = planRepo.findById(planId);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		String loggedInUsername = auth.getName(),
				leaderUsername = generalPlan.getLeader().getUsername();
		
		return loggedInUsername.equals(leaderUsername);
	}
	
	public boolean checkLoggedInUserAsLeader(GeneralPlan generalPlan) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		String loggedInUsername = auth.getName(),
				leaderUsername = generalPlan.getLeader().getUsername();
		
		return loggedInUsername.equals(leaderUsername);
	}

}
