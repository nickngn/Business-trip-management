package com.hung.project1.config;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.hung.project1.entity.Role;
import com.hung.project1.entity.User;
import com.hung.project1.repository.GeneralPlanRepository;
import com.hung.project1.repository.RoleRepository;
import com.hung.project1.repository.UserRepository;

@Component
public class ApplicationSeeding implements ApplicationListener<ContextRefreshedEvent>{
	Logger logger = LoggerFactory.getLogger(ApplicationSeeding.class);
	
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private GeneralPlanRepository planRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// seeding roles
		if (roleRepo.findByName("ROLE_DIRECTOR") == null) {
			Role role = new Role();
			role.setName("ROLE_DIRECTOR");
			roleRepo.save(role);
		}
		
		if (roleRepo.findByName("ROLE_EMPLOYEE") == null) {
			Role role = new Role();
			role.setName("ROLE_EMPLOYEE");
			roleRepo.save(role);
		}
		
		if (roleRepo.findByName("ROLE_ACCOUNTANT") == null) {
			Role role = new Role();
			role.setName("ROLE_ACCOUNTANT");
			roleRepo.save(role);
		}
		
		if (roleRepo.findByName("ROLE_SYSTEM_MANAGEMENT") == null) {
			Role role = new Role();
			role.setName("ROLE_SYSTEM_MANAGEMENT");
			roleRepo.save(role);
		}
		//seeding accounts
		if (userRepo.findByUsername("admin1234") == null) {
			User user = new User();
			user.setUsername("admin1234");
			user.setPassword(passwordEncoder.encode("andyhung"));
			user.setWorkUnit("IT");
			user.setRole(roleRepo.findByName("ROLE_SYSTEM_MANAGEMENT"));
			userRepo.save(user);
		}
		
//		for(User user : userRepo.findAll()) {
//			user.setPassword(passwordEncoder.encode(user.getUsername()));
//			userRepo.save(user);
//		}
		
//		for(Plan plan : planRepo.findAll()) {
//			double x = Math.random() *3;
//			if (x < 0.3) {
//				plan.setConfirmed(null);
//				System.out.println("null");
//			} else if (x < 2) {
//				plan.setConfirmed(true);
//				System.out.println("true");
//			} else {
//				plan.setConfirmed(false);
//				System.out.println("false");
//			}
//			planRepo.save(plan);
//			
//		}
		
//		for(Plan plan: planRepo.findAll()) {
//			int rand = (int) (Math.random() * 500);
//			plan.setLeader(userRepo.findById(rand));
//			planRepo.save(plan);
//		}
		
	}

}
