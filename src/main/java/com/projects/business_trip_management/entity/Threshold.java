package com.projects.business_trip_management.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name="threshold")
public class Threshold implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3261965131375230754L;

	@Id @GeneratedValue
	@Column(name="id")
	private Integer id;

	@NotEmpty(message="Phí không được để trống")
	private String fee;
	
	@NotNull(message="Hạn mức không được để trống")
	private Double amount;
	
	public Threshold() {
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
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	
}
