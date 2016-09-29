package com.tf.usermanagement.dto;

public class UserOrgMapDto {

	private long userOrgMapId;

	public long getUserOrgMapId() {
		return userOrgMapId;
	}

	public void setUserOrgMapId(long userOrgMapId) {
		this.userOrgMapId = userOrgMapId;
	}

	@Override
	public String toString() {
		return "UserOrgMapDto [userOrgMapId=" + userOrgMapId + "]";
	}
	
}
