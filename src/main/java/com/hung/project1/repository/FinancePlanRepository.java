package com.hung.project1.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.hung.project1.entity.FinancePlan;

public interface FinancePlanRepository extends CrudRepository<FinancePlan, Integer>{

	List<FinancePlan> findByGeneralPlanId(int planId);
}
