package com.hung.project1.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "notification")
public class Notification implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	@Column(name="id")
	private Integer id;
	
	@NotEmpty(message="Tên không được để trống")
	private String name;
	
	@NotNull(message="Số lượng người phải lớn hơn 1")
	@Min(value=1, message="Số lượng người phải lớn hơn 1")
	private int personQuantity;
	
	@NotEmpty(message="Nơi công tác không được để trống")
	private String location;
	
	@DateTimeFormat(pattern="mm-dd-yyyy")
	@NotNull(message="Ngày bắt đầu không được để trống")
	private Date startDate;
	
	@DateTimeFormat(pattern="mm-dd-yyyy")
	private Date endDate;
	
	private String description;
	
	private String attachment;

	public Notification() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPersonQuantity() {
		return personQuantity;
	}

	public void setPersonQuantity(int personQuantity) {
		this.personQuantity = personQuantity;
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

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Notification [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", personQuantity=");
		builder.append(personQuantity);
		builder.append(", location=");
		builder.append(location);
		builder.append(", startDate=");
		builder.append(startDate);
		builder.append(", endDate=");
		builder.append(endDate);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}

}
