package com.tf.usermanagement.dto;

import java.util.Date;

/**
 * This DTO contains the fields that are used to 
 * get the role count for selected user for each of the organization 
 * he belongs to
 * @author Manideep
 *
 */
public class OrganizationRoleCountDto {
	
	private Date createdDate;
	private Date lastLoginDate;
	private Date modifiedDate;
	private long organizationId;
	private long roleCount;
	private long approvalStatus;
	private String organizationName;
	
	//getters and setters
	
	
	public long getOrganizationId() {
		return organizationId;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	public void setOrganizationId(long organizationId) {
		this.organizationId = organizationId;
	}
	public long getRoleCount() {
		return roleCount;
	}
	public void setRoleCount(long roleCount) {
		this.roleCount = roleCount;
	}
	
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public long getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(long approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	//to string
	@Override
	public String toString() {
		return "OrganizationRoleCountDto [createdDate=" + createdDate + ", lastLoginDate=" + lastLoginDate
				+ ", modifiedDate=" + modifiedDate + ", organizationId=" + organizationId + ", roleCount=" + roleCount
				+ ", approvalStatus=" + approvalStatus + ", organizationName=" + organizationName + "]";
	}
	
	
	
	
	
	
	
	
	
}
