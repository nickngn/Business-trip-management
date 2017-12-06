package com.hung.project1.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="user")
public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2933327977572476203L;

	@Id @GeneratedValue
	@Column(name="id")
	private Integer id;
	
	@NotEmpty(message="Username không được để trống")
	private String username;
	
	@NotEmpty(message="Mật khẩu không được để trống")
	private String password;
	
	@NotEmpty(message="Vị trí không được để trống")
	private String position;
	
	@NotEmpty(message="Đơn vị không được để trống")
	private String workUnit;
	
	private String phone;
	
	@JsonIgnoreProperties(value={"leader", "personelPlanList", "personelIncurredPlanList"})
	@OneToMany(mappedBy="leader", fetch = FetchType.LAZY)
	private List<GeneralPlan> generalPlans;
	
	@ManyToOne()
	@JoinColumn(name="role_id")
	private Role role;
	
	public User() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
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
	
	
	public List<GeneralPlan> getPlans() {
		return generalPlans;
	}
	public void setPlans(List<GeneralPlan> generalPlans) {
		this.generalPlans = generalPlans;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", position=" + position + ", workUnit=" + workUnit + ", phone="
				+ phone + "]";
	}
	
	

}
