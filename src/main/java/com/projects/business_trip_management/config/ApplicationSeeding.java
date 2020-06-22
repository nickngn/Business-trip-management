package com.projects.business_trip_management.config;

import javax.transaction.Transactional;

import com.projects.business_trip_management.entity.Role;
import com.projects.business_trip_management.entity.User;
import com.projects.business_trip_management.repository.GeneralPlanRepository;
import com.projects.business_trip_management.repository.RoleRepository;
import com.projects.business_trip_management.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

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
		if (userRepo.findByUsername("admin") == null) {
			User user = new User();
			user.setUsername("admin");
			user.setPassword(passwordEncoder.encode("admin"));
			user.setWorkUnit("IT");
			user.setPosition("Admin");
			user.setRole(roleRepo.findByName("ROLE_SYSTEM_MANAGEMENT"));
			userRepo.save(user);
		}

		if (userRepo.findByUsername("director1") == null) {
			User user = new User();
			user.setUsername("director1");
			user.setPassword(passwordEncoder.encode("director1"));
			user.setWorkUnit("Director 1");
			user.setPosition("Director");
			user.setRole(roleRepo.findByName("ROLE_DIRECTOR"));
			userRepo.save(user);
		}

		if (userRepo.findByUsername("employee1") == null) {
			User user = new User();
			user.setUsername("employee1");
			user.setPassword(passwordEncoder.encode("employee1"));
			user.setWorkUnit("employee 1");
			user.setPosition("Employee");
			user.setRole(roleRepo.findByName("ROLE_EMPLOYEE"));
			userRepo.save(user);
		}
		
		if (userRepo.findByUsername("acountant1") == null) {
			User user = new User();
			user.setUsername("acountant1");
			user.setPassword(passwordEncoder.encode("acountant1"));
			user.setWorkUnit("acountant 1");
			user.setPosition("Accountant");
			user.setRole(roleRepo.findByName("ROLE_ACCOUNTANT"));
			userRepo.save(user);
		}
//		for(User user : userRepo.findAll()) {
//			user.setPassword(passwordEncoder.encode(user.getUsername()));
//			userRepo.save(user);
//		}
		
//		for(Plan plan : planRepo.findAll()) {
//			double x = Math.random() *3;
//			if (x < 0.3) {
//				plan.setOn_Going(null);
//				System.out.println("null");
//			} else if (x < 2) {
//				plan.setOn_Going(true);
//				System.out.println("true");
//			} else {
//				plan.setOn_Going(false);
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
