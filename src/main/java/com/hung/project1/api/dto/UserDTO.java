package com.hung.project1.api.dto;

import org.springframework.hateoas.ResourceSupport;

import com.hung.project1.entity.Role;

public class UserDTO extends ResourceSupport{
	
	private String username;
	
	private String position;
	
	private String workUnit;
	
	private String phone;
	
	private Role role;
	
	public UserDTO() {
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getWorkUnit() {
		return workUnit;
	}

	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
}
