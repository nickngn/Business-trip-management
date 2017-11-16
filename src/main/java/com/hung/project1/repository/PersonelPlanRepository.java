package com.hung.project1.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.hung.project1.entity.PersonelPlan;

public interface PersonelPlanRepository extends CrudRepository<PersonelPlan, Integer>{

	List<PersonelPlan> findByGeneralPlanId(Integer planId);
	
	List<PersonelPlan> findByUserId(Integer userId);
	
//	List<PersonelPlan> findByPlanId(Integer planId);
}
