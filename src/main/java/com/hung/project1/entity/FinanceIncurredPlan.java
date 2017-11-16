package com.hung.project1.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="finance_incurred_plan")
public class FinanceIncurredPlan implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4279589269539315789L;
	
	@Id @GeneratedValue
	@Column(name="id")
	private Integer id;
	private String fee;
	private Double cost;
	private String description;
	
	@JsonIgnoreProperties("financeIncurredPlanList")
	@ManyToOne
	@JoinColumn(name="plan_id")
	private GeneralPlan generalPlan;
	
	@Column(name="is_confirmed")
	private boolean isConfirmed;
	
	public FinanceIncurredPlan() {
		// TODO Auto-generated constructor stub
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFee() {
		return fee;
	}
	public void setFee(String fee) {
		this.fee = fee;
	}
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	public GeneralPlan getPlan() {
		return generalPlan;
	}
	public void setPlan(GeneralPlan generalPlan) {
		this.generalPlan = generalPlan;
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
		return "FinanceIncurredPlan [id=" + id + ", fee=" + fee + ", cost=" + cost + ", description=" + description
				+ ", plan=" + generalPlan + ", isConfirmed=" + isConfirmed + "]";
	}
	
	

}
