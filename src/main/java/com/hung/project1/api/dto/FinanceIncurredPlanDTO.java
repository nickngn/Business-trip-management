package com.hung.project1.api.dto;

public class FinanceIncurredPlanDTO {

	private String fee;
	private Double cost;
	private String description;
	
	private int generalPlanId;
	
	private boolean isConfirmed;

	public FinanceIncurredPlanDTO() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getGeneralPlanId() {
		return generalPlanId;
	}

	public void setGeneralPlanId(int generalPlanId) {
		this.generalPlanId = generalPlanId;
	}

	public boolean isConfirmed() {
		return isConfirmed;
	}

	public void setConfirmed(boolean isConfirmed) {
		this.isConfirmed = isConfirmed;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FinanceIncurredPlanDTO [fee=");
		builder.append(fee);
		builder.append(", cost=");
		builder.append(cost);
		builder.append(", description=");
		builder.append(description);
		builder.append(", generalPlanId=");
		builder.append(generalPlanId);
		builder.append(", isConfirmed=");
		builder.append(isConfirmed);
		builder.append("]");
		return builder.toString();
	}
	
	
}
