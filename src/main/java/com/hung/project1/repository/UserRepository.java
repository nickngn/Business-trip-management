package com.hung.project1.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.hung.project1.entity.User;

public interface UserRepository extends Repository<User, Integer>{
	
	List<User> findAll();
	
	Page<User> findAll(Pageable pageRequest);
	
	User findByUsername(String username);
	User findById(Integer id);
	
//	void create(User user);
	void save(User user);
	void delete(User user);
	
	Page<User> findByUsernameContaining(String username, Pageable pageRequest);
	
	@Query("FROM User u where u.role.name = 'ROLE_EMPLOYEE' ")
	List<User> findAllEmployee();
}
