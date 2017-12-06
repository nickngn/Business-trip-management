package com.hung.project1.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="personel_plan")
@JsonIgnoreProperties("generalPlan")
public class PersonelPlan implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6283363623397838835L;

	@Id @GeneratedValue
	@Column(name="id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="plan_id")
	private GeneralPlan generalPlan;
	
	@JsonIgnoreProperties("plans")
	@ManyToOne
	@JoinColumn(name="user_id")
	@NotNull(message="Nhân viên không được để trống")
	private User user;
	
	public PersonelPlan() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public GeneralPlan getPlan() {
		return generalPlan;
	}

	public void setPlan(GeneralPlan generalPlan) {
		this.generalPlan = generalPlan;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "PersonelPlan [id=" + id + ", plan=" + generalPlan + ", user=" + user + "]";
	}
	
	
	
}
