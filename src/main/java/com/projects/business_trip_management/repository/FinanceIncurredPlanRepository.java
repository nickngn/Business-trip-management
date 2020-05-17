package com.projects.business_trip_management.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.projects.business_trip_management.entity.FinanceIncurredPlan;

public interface FinanceIncurredPlanRepository extends CrudRepository<FinanceIncurredPlan, Integer>{
	
	List<FinanceIncurredPlan> findByGeneralPlanId(int id);

}
