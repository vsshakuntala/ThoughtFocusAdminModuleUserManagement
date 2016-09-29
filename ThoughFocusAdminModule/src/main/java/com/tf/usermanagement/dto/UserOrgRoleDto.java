package com.tf.usermanagement.dto;

public class UserOrgRoleDto {

	//private long userId;
	private long orgId;
	//private long roleId;
	
	public UserOrgRoleDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public UserOrgRoleDto(long orgId) {
		super();
		this.orgId = orgId;
	}

//	public UserOrgRoleDto(long userId, long orgId, long roleId) {
//		super();
//		this.userId = userId;
//		this.orgId = orgId;
//		this.roleId = roleId;
//	}

//	public long getUserId() {
//		return userId;
//	}
//
//	public void setUserId(long userId) {
//		this.userId = userId;
//	}

	public long getOrgId() {
		return orgId;
	}

	public void setOrgId(long orgId) {
		this.orgId = orgId;
	}

//	public long getRoleId() {
//		return roleId;
//	}
//
//	public void setRoleId(long roleId) {
//		this.roleId = roleId;
//	}

	@Override
	public String toString() {
		//return "UserOrgRoleDto [userId=" + userId + ", orgId=" + orgId + ", roleId=" + roleId + "]";
		return "UserOrgRoleDto [orgId=" + orgId + "]";
	}
	
	
	
}
