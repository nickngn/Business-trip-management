package com.hung.project1.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.hung.project1.entity.Plan;

public interface PlanRepository extends CrudRepository<Plan, Integer>{
	
	@Query(value="FROM Plan p WHERE p.isConfirmed='' ORDER BY p.startDate DESC")
	Page<Plan> findNotConfirmedPlan(Pageable pageRequest);
}
