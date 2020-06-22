package com.projects.business_trip_management.repository;


import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import com.projects.business_trip_management.entity.Threshold;

public interface ThresholdRepository extends CrudRepository<Threshold, Integer>{

	List<Threshold> findAll();
	
	Threshold save(Threshold generalPlan);
	
}
