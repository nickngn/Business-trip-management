package com.hung.project1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.hung.project1.entity.PersonelIncurredPlan;

public interface PersonelIncurredPlanRepository extends Repository<PersonelIncurredPlan, Integer>{

	@Query("FROM PersonelIncurredPlan p WHERE p.user.id=:userId AND p.action='ATTEND' AND p.isConfirmed='true'")
	public List<PersonelIncurredPlan> findByAttendedUser(@Param("userId") int userId);
}
