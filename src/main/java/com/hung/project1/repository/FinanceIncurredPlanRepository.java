package com.hung.project1.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.hung.project1.entity.FinanceIncurredPlan;

public interface FinanceIncurredPlanRepository extends CrudRepository<FinanceIncurredPlan, Integer>{
	
	List<FinanceIncurredPlan> findByGeneralPlanId(int id);

}
