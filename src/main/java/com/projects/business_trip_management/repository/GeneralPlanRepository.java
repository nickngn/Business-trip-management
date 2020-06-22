package com.projects.business_trip_management.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.projects.business_trip_management.entity.GeneralPlan;

public interface GeneralPlanRepository extends Repository<GeneralPlan, Integer>{
	
	Page<GeneralPlan> findAll(Pageable pageRequest);
	
	GeneralPlan findById(Integer id);
	
	void save(GeneralPlan generalPlan);
	
	@Query("SELECT coalesce(max(ch.id), 0) FROM GeneralPlan ch")
	Integer findMaxId();
	
	@Query(value="FROM GeneralPlan p WHERE p.status='Unconfirmed' ORDER BY p.startDate DESC")
	Page<GeneralPlan> findUnconfirmedPlan(Pageable pageRequest);
	
	@Query(value="FROM GeneralPlan p WHERE p.status='Finished' OR p.status='Denied' ORDER BY p.startDate DESC")
	Page<GeneralPlan> findConfirmedOrFinishedPlans(Pageable pageRequest);
	
	@Query(value="FROM GeneralPlan p WHERE p.status='On_Going' ORDER BY p.startDate DESC")
	Page<GeneralPlan> findAcceptedPlan(Pageable pageRequest);
	
	@Query(value="SELECT p FROM GeneralPlan p where p.leader.id= :leaderId")
	Page<GeneralPlan> findByLeaderId(@Param("leaderId") int leaderId, Pageable pageRequest);
	
	
	
}
