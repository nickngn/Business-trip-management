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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="plan")
@JsonIgnoreProperties({
	"personelPlanList",
	"personelIncurredPlanList",
	"financePlanList",
	"financeIncurredPlanList"
})
public class Plan implements Serializable{
	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 2940179182335628091L;

	@Id @GeneratedValue
	@Column(name="id")
	private Integer id;
	
    private String name;
    
    private String location;
    
    @Column(name="start_date")
    private Date startDate;
    
    @Column(name="finish_date")
    private Date finishDate;
    
    @JsonIgnoreProperties({"plans", "password"})
    @ManyToOne
    @JoinColumn(name="leader_id")
    private User leader;
    
    @Column(name="is_confirmed")
    private Boolean isConfirmed;
    
    @OneToMany(mappedBy="plan")
    private List<PersonelPlan> personelPlanList;
    
    @OneToMany(mappedBy="plan")
    private List<PersonelIncurredPlan> personelIncurredPlanList;
    
    @OneToMany(mappedBy="plan")
    private List<FinancePlan> financePlanList;
    
    @OneToMany(mappedBy="plan")
    private List<FinanceIncurredPlan> financeIncurredPlanList;
    
	public Plan() {
		super();
	}

	public Plan(String name, String location, Date startDate, Date finishDate) {
		super();
		this.name = name;
		this.location = location;
		this.startDate = startDate;
		this.finishDate = finishDate;
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

	public boolean isConfirmed() {
		return isConfirmed;
	}

	public void setConfirmed(Boolean isConfirmed) {
		this.isConfirmed = isConfirmed;
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

	@Override
	public String toString() {
		return "Plan [id=" + id + ", name=" + name + ", location=" + location + ", startDate=" + startDate
				+ ", finishDate=" + finishDate + "]";
	}
    
    
    
}
