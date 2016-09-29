package com.tf.usermanagement.dto;

public class PermissionsDto {

	private Long permissionId;
	private String permissionName;
	
	public PermissionsDto(Long permissionId, String permissionName) {
		super();
		this.permissionId = permissionId;
		this.permissionName = permissionName;
	}
	public PermissionsDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}
	public String getPermissionName() {
		return permissionName;
	}
	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}
	
	
}
