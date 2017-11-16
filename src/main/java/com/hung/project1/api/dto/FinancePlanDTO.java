package com.hung.project1.api.dto;

import org.springframework.hateoas.ResourceSupport;

public class FinancePlanDTO extends ResourceSupport {

	private Integer generalPlanId;
	private String fee;
	private Double cost;
	
	public FinancePlanDTO() {
	}

	public Integer getGeneralPlanId() {
		return generalPlanId;
	}

	public void setGeneralPlanId(Integer generalPlanId) {
		this.generalPlanId = generalPlanId;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FinancePlanDTO [generalPlanId=");
		builder.append(generalPlanId);
		builder.append(", fee=");
		builder.append(fee);
		builder.append(", cost=");
		builder.append(cost);
		builder.append("]");
		return builder.toString();
	}

		
}
