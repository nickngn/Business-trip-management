package com.hung.project1.repository;

import org.springframework.data.repository.CrudRepository;

import com.hung.project1.entity.Role;

public interface RoleRepository extends CrudRepository<Role, Integer>{

	Role findByName(String name);
}
