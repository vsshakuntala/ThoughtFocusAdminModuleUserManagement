package com.tf.usermanagement.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SALES_AREA")
public class SalesArea implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long salesAreaId;
	private String salesOrgReference;
	private String salesOrgName;
	private String distributionChannelRef;
	private String distributionChannelName;
	private String divisionReference;
	private Integer organizationId;
	private String description;
	private Long createdBy;
	private Date createdDate;
	private Long modifiedBy;
	private Date modifiedDate;
	private Boolean active;
	
	@Id
	@Column(name="SALES_AREA_ID")
	public Long getSalesAreaId() {
		return salesAreaId;
	}
	public void setSalesAreaId(Long salesAreaId) {
		this.salesAreaId = salesAreaId;
	}
	
	@Column(name="SALES_ORG_REFERENCE")
	public String getSalesOrgReference() {
		return salesOrgReference;
	}
	public void setSalesOrgReference(String salesOrgReference) {
		this.salesOrgReference = salesOrgReference;
	}
	
	@Column(name="SALES_ORG_NAME")
	public String getSalesOrgName() {
		return salesOrgName;
	}
	public void setSalesOrgName(String salesOrgName) {
		this.salesOrgName = salesOrgName;
	}
	
	@Column(name="DISTRIBUTION_CHANNEL_REF")
	public String getDistributionChannelRef() {
		return distributionChannelRef;
	}
	public void setDistributionChannelRef(String distributionChannelRef) {
		this.distributionChannelRef = distributionChannelRef;
	}
	
	@Column(name="DISTRIBUTION_CHANNEL_NAME")
	public String getDistributionChannelName() {
		return distributionChannelName;
	}
	public void setDistributionChannelName(String distributionChannelName) {
		this.distributionChannelName = distributionChannelName;
	}
	
	@Column(name="DIVISION_REFERENCE")
	public String getDivisionReference() {
		return divisionReference;
	}
	public void setDivisionReference(String divisionReference) {
		this.divisionReference = divisionReference;
	}
	
	@Column(name="ORGANIZATION_ID")
	public Integer getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}
	
	@Column(name="DESCRIPTION")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name="CREATED_BY")
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	
	@Column(name="CREATED_DATE")
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	@Column(name="MODIFIED_BY")
	public Long getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	@Column(name="MODIFIED_DATE")
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	@Column(name="ACTIVE")
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	@Override
	public String toString() {
		return "SalesArea [salesAreaId=" + salesAreaId + ", salesOrgReference=" + salesOrgReference + ", salesOrgName="
				+ salesOrgName + ", distributionChannelRef=" + distributionChannelRef + ", distributionChannelName="
				+ distributionChannelName + ", divisionReference=" + divisionReference + ", organizationId="
				+ organizationId + ", description=" + description + ", createdBy=" + createdBy + ", createdDate="
				+ createdDate + ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate + ", active=" + active
				+ "]";
	}
	
}
