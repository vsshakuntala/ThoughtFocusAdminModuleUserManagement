package com.tf.usermanagement.dto;

/**
 * this class contains the data of user organization list
 * in which user is unAssigned to organization with admin id
 *  
 * @author Manideep
 *
 */
public class UserUnassignedOrgDto {

	private long organizationId;
	private String organizationName;
	private long adminId;
	private boolean isAdminAccess;
	
	
	//setters and getters
	public long getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(long organizationId) {
		this.organizationId = organizationId;
	}
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	public long getAdminId() {
		return adminId;
	}
	public void setAdminId(long adminId) {
		this.adminId = adminId;
	}
	public boolean isAdminAccess() {
		return isAdminAccess;
	}
	public void setAdminAccess(boolean isAdminAccess) {
		this.isAdminAccess = isAdminAccess;
	}
	
	
	
	
}
