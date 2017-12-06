package com.hung.project1.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="plan")
@JsonIgnoreProperties({
	"personelPlanList",
	"personelIncurredPlanList",
	"financePlanList",
	"financeIncurredPlanList"
})
public class GeneralPlan implements Serializable{
	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 2940179182335628091L;

	@Id @GeneratedValue
	@Column(name="id")
	private Integer id;
	
	@NotEmpty(message="Tên không được để trống")
	private String name;
    
	@NotEmpty(message="Nơi công tác không được để trống")
	private String location;
    
	@NotNull(message="Thời gian bắt đầu không được để trống")
    @Column(name="start_date")
    @DateTimeFormat(pattern="yy-MM-dd")
    private Date startDate;
    
    @Column(name="finish_date")
    @DateTimeFormat(pattern="yy-MM-dd")
    private Date finishDate;
    
    @JsonIgnoreProperties({"plans", "password"})
    @ManyToOne
    @JoinColumn(name="leader_id")
    private User leader;
    
    private String status;
    
    @Column(name="total_cost")
    private double cost;
    
    @OneToMany(mappedBy="generalPlan")
    private List<PersonelPlan> personelPlanList;
    
    @OneToMany(mappedBy="generalPlan")
    private List<PersonelIncurredPlan> personelIncurredPlanList;
    
    @OneToMany(mappedBy="generalPlan")
    private List<FinancePlan> financePlanList;
    
    @OneToMany(mappedBy="generalPlan")
    private List<FinanceIncurredPlan> financeIncurredPlanList;
    
	public GeneralPlan() {
		super();
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

	public List<PersonelPlan> getPersonelPlanList() {
		return personelPlanList;
	}


	public void setPersonelPlanList(List<PersonelPlan> personelPlanList) {
		this.personelPlanList = personelPlanList;
	}

	public List<FinancePlan> getFinancePlanList() {
		return financePlanList;
	}

	public void setFinancePlanList(List<FinancePlan> financePlanList) {
		this.financePlanList = financePlanList;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getLeader() {
		return leader;
	}

	public void setLeader(User leader) {
		this.leader = leader;
	}

	public List<PersonelIncurredPlan> getPersonelIncurredPlanList() {
		return personelIncurredPlanList;
	}

	public void setPersonelIncurredPlanList(List<PersonelIncurredPlan> personelIncurredPlanList) {
		this.personelIncurredPlanList = personelIncurredPlanList;
	}

	public List<FinanceIncurredPlan> getFinanceIncurredPlanList() {
		return financeIncurredPlanList;
	}

	public void setFinanceIncurredPlanList(List<FinanceIncurredPlan> financeIncurredPlanList) {
		this.financeIncurredPlanList = financeIncurredPlanList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GeneralPlan [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", location=");
		builder.append(location);
		builder.append(", startDate=");
		builder.append(startDate);
		builder.append(", finishDate=");
		builder.append(finishDate);
		builder.append(", leader=");
		builder.append(leader);
		builder.append(", status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}
    
    
    
}
