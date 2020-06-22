package com.projects.business_trip_management.api.dto;

public class FinanceIncurredPlanDTO {

	private String fee;
	private Double cost;
	private String description;
	
	private int generalPlanId;
	
	private boolean isOn_Going;

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

	public boolean isOn_Going() {
		return isOn_Going;
	}

	public void setOn_Going(boolean isOn_Going) {
		this.isOn_Going = isOn_Going;
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
		builder.append(", isOn_Going=");
		builder.append(isOn_Going);
		builder.append("]");
		return builder.toString();
	}
	
	
}
