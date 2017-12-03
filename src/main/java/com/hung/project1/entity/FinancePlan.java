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
@Table(name="finance_plan")
public class FinancePlan implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4070738025358425172L;
	
	@Id @GeneratedValue
	@Column(name="id")
	private Integer id;
	private String fee;
	private Double cost;
	private double tax;
	private String description;
	
	@JsonIgnoreProperties("financePlanList")
	@ManyToOne
	@JoinColumn(name="plan_id")
	private GeneralPlan generalPlan;
	
	public FinancePlan() {
		super();
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


	public double getTax() {
		return tax;
	}


	public void setTax(double tax) {
		this.tax = tax;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public GeneralPlan getGeneralPlan() {
		return generalPlan;
	}


	public void setGeneralPlan(GeneralPlan generalPlan) {
		this.generalPlan = generalPlan;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return "FinancePlan [id=" + id + ", fee=" + fee + ", cost=" + cost + ", plan=" + generalPlan + "]";
	}
	
	
	
}
