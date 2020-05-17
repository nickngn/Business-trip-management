package com.projects.business_trip_management.repository;

import org.springframework.data.repository.CrudRepository;

import com.projects.business_trip_management.entity.Role;

public interface RoleRepository extends CrudRepository<Role, Integer>{

	Role findByName(String name);
}
