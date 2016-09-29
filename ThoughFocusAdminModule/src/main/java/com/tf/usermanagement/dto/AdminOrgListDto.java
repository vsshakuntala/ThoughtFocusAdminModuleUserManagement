package com.tf.usermanagement.dto;

/**
 * this class contains the logged in adminId,RoleId and organization Id
 * @author Manideep
 *
 */
public class AdminOrgListDto {

	private long adminUserId;
	private long roleId;
	private long organizationId;
	private String organizationName;
	
	//setters and getters
	public long getAdminUserId() {
		return adminUserId;
	}
	public void setAdminUserId(long adminUserId) {
		this.adminUserId = adminUserId;
	}
	public long getRoleId() {
		return roleId;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
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

}
