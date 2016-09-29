package com.tf.usermanagement.dto;

import java.util.Date;

/**
 * 
 * @author Manideep
 *
 */
public class DivisionAssignmentDto {

	private String status;
	private String OrganizationName;
	private long organizationId;
	private long roleCount;
	private long totalCustomerCount;
	private long defaultAddressCount;
	private long totalCatalogCount;
	private long groupsCount;
	private boolean isAdminAccess;
	private Date createdDate;
	private Date lastLoginDate;

	// setters and getters
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrganizationName() {
		return OrganizationName;
	}

	public void setOrganizationName(String organizationName) {
		OrganizationName = organizationName;
	}

	public long getRoleCount() {
		return roleCount;
	}

	public void setRoleCount(long roleCount) {
		this.roleCount = roleCount;
	}

	
	public long getDefaultAddressCount() {
		return defaultAddressCount;
	}

	public void setDefaultAddressCount(long defaultAddressCount) {
		this.defaultAddressCount = defaultAddressCount;
	}

	

	public long getGroupsCount() {
		return groupsCount;
	}

	public void setGroupsCount(long groupsCount) {
		this.groupsCount = groupsCount;
	}

	public long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(long organizationId) {
		this.organizationId = organizationId;
	}

	public boolean isAdminAccess() {
		return isAdminAccess;
	}

	public void setAdminAccess(boolean isAdminAccess) {
		this.isAdminAccess = isAdminAccess;
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

	public long getTotalCustomerCount() {
		return totalCustomerCount;
	}

	public void setTotalCustomerCount(long totalCustomerCount) {
		this.totalCustomerCount = totalCustomerCount;
	}

	public long getTotalCatalogCount() {
		return totalCatalogCount;
	}

	public void setTotalCatalogCount(long totalCatalogCount) {
		this.totalCatalogCount = totalCatalogCount;
	}

	@Override
	public String toString() {
		return "DivisionAssignmentDto [status=" + status + ", OrganizationName=" + OrganizationName
				+ ", organizationId=" + organizationId + ", roleCount=" + roleCount + ", totalCustomerCount="
				+ totalCustomerCount + ", defaultAddressCount=" + defaultAddressCount + ", totalCatalogCount="
				+ totalCatalogCount + ", groupsCount=" + groupsCount + ", isAdminAccess=" + isAdminAccess
				+ ", createdDate=" + createdDate + ", lastLoginDate=" + lastLoginDate + "]";
	}

	
}
