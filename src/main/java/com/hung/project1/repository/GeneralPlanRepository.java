package com.hung.project1.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.hung.project1.entity.GeneralPlan;

public interface GeneralPlanRepository extends Repository<GeneralPlan, Integer>{
	
	Page<GeneralPlan> findAll(Pageable pageRequest);
	
	GeneralPlan findById(Integer id);
	
	void save(GeneralPlan generalPlan);
	
	@Query("SELECT coalesce(max(ch.id), 0) FROM GeneralPlan ch")
	Integer findMaxId();
	
	@Query(value="FROM GeneralPlan p WHERE p.status='Chưa duyệt' ORDER BY p.startDate DESC")
	Page<GeneralPlan> findUnconfirmedPlan(Pageable pageRequest);
	
	@Query(value="FROM GeneralPlan p WHERE p.status='Đã hoàn thành' OR p.status='Đã từ chối' ORDER BY p.startDate DESC")
	Page<GeneralPlan> findConfirmedOrFinishedPlans(Pageable pageRequest);
	
	@Query(value="FROM GeneralPlan p WHERE p.status='Đã đồng ý' ORDER BY p.startDate DESC")
	Page<GeneralPlan> findAcceptedPlan(Pageable pageRequest);
	
	@Query(value="SELECT p FROM GeneralPlan p where p.leader.id= :leaderId")
	Page<GeneralPlan> findByLeaderId(@Param("leaderId") int leaderId, Pageable pageRequest);
	
	
	
}
