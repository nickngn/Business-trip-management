package com.projects.business_trip_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.projects.business_trip_management.entity.PersonelIncurredPlan;

public interface PersonelIncurredPlanRepository extends Repository<PersonelIncurredPlan, Integer>{
	
	List<PersonelIncurredPlan> findByGeneralPlanId(int id);
	
	void save(PersonelIncurredPlan personelIncurredPlan);

	@Query("FROM PersonelIncurredPlan p WHERE p.user.id=:userId AND p.action='ATTEND' AND p.isOn_Going='true'")
	public List<PersonelIncurredPlan> findByAttendedUser(@Param("userId") int userId);
}
