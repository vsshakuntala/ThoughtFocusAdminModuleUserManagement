package com.tf.usermanagement.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "BRANCH_PLANT")
public class BranchPlant implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer branchId;
	private Integer organizationId;
	private String plantReference;
	private Integer priority;
	private Boolean active;
	private String companyReference;
	
	
	
	
	@Column(name = "ORGANIZATION_ID", insertable=false, updatable=false)
	public Integer getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}
	
	@Column(name = "PLANT_REFERENCE", insertable=false, updatable=false)
	public String getPlantReference() {
		return plantReference;
	}
	public void setPlantReference(String plantReference) {
		this.plantReference = plantReference;
	}
	
	@Column(name = "PRIORITY")
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	
	@Column(name = "ACTIVE")
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	@Id
	@Column(name = "BRANCH_ID")
	public Integer getBranchId() {
		return branchId;
	}
	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}
	@Column(name = "COMPANY_REFERENCE", insertable=false, updatable=false)
	public String getCompanyReference() {
		return companyReference;
	}
	public void setCompanyReference(String companyReference) {
		this.companyReference = companyReference;
	}
	@Override
	public String toString() {
		return "UserCatalog [branchID: "+branchId+", PRIORITY: "+priority+", ACTIVE: "+active+
				", ORGANIZATION_ID: "+organizationId+", PLANT_NUMBER: "+plantReference+"] ";
	}
	
}
