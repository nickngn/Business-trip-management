package com.hung.project1.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="personel_incurred_plan")
public class PersonelIncurredPlan implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2358456497386911042L;

	@Id @GeneratedValue
	@Column(name="id")
	private Integer id;
	
	@JsonIgnoreProperties({
		"personelIncurredPlanList", 
		"financePlanList", 
		"financeIncurredPlanList"})
	@ManyToOne
	@JoinColumn(name="plan_id")
	private GeneralPlan generalPlan;
	
	@NotNull(message="Nhân viên không được để trống")
	@JsonIgnoreProperties("plans")
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@NotEmpty(message="Thay đổi không được để trống")
	private String action;
	
	@NotNull(message="Ngày không được để trống")
	private Date date;
	
	@NotEmpty(message="Ghi chú được để trống")
	private String description;
	
	@Column(name="is_confirmed")
	private boolean isConfirmed;
	
	public PersonelIncurredPlan() {
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
	
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isConfirmed() {
		return isConfirmed;
	}

	public void setConfirmed(boolean isConfirmed) {
		this.isConfirmed = isConfirmed;
	}

	@Override
	public String toString() {
		return "PersonelIncurredPlan [id=" + id + ", plan=" + generalPlan + ", user=" + user + ", action=" + action + ", date="
				+ date + ", description=" + description + ", isConfirmed=" + isConfirmed + "]";
	}

	
	
}
