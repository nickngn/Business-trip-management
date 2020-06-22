package com.projects.business_trip_management.api.dto;

import java.util.Date;

public class PersonelIncurredPlanDTO {

	private int generalPlanId;
	
	private int userId;
	
	private String action;
	
	private Date date;
	
	private String description;
	
	private boolean isOn_Going;
	
	

	public PersonelIncurredPlanDTO() {
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

	public boolean isOn_Going() {
		return isOn_Going;
	}

	public void setOn_Going(boolean isOn_Going) {
		this.isOn_Going = isOn_Going;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PersonelIncurredPlanDTO [generalPlanId=");
		builder.append(generalPlanId);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", action=");
		builder.append(action);
		builder.append(", date=");
		builder.append(date);
		builder.append(", description=");
		builder.append(description);
		builder.append(", isOn_Going=");
		builder.append(isOn_Going);
		builder.append("]");
		return builder.toString();
	}
	
	
}
