package com.hung.project1.api.dto;

import org.springframework.hateoas.ResourceSupport;

public class PersonelPlanDTO extends ResourceSupport {
	
	private int generalPlanId;
	
	private int userId;

	public PersonelPlanDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getGeneralPlanId() {
		return generalPlanId;
	}

	public void setGeneralPlanId(int generalPlanId) {
		this.generalPlanId = generalPlanId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PersonelPlanDTO [generalPlanId=");
		builder.append(generalPlanId);
		builder.append(", userId=");
		builder.append(userId);
		builder.append("]");
		return builder.toString();
	}
	
	
}
