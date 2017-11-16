package com.hung.project1.api.dto;

import java.util.Date;

import org.springframework.hateoas.ResourceSupport;

public class GeneralPlanDTO extends ResourceSupport{
	
    private String name;
    
    private String location;
    
    private Date startDate;
    
    private Date finishDate;
    
    private String status;

	public GeneralPlanDTO() {
	}	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GeneralPlanDTO [name=");
		builder.append(name);
		builder.append(", location=");
		builder.append(location);
		builder.append(", startDate=");
		builder.append(startDate);
		builder.append(", finishDate=");
		builder.append(finishDate);
		builder.append(", status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}

    
}
