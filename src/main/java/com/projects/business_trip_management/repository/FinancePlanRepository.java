package com.projects.business_trip_management.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.projects.business_trip_management.entity.FinancePlan;

public interface FinancePlanRepository extends CrudRepository<FinancePlan, Integer>{

	List<FinancePlan> findByGeneralPlanId(int planId);
}
