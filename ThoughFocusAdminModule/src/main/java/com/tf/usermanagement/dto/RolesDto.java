package com.tf.usermanagement.dto;

public class RolesDto {

	private Long roleId;
    private String roleName;
    
    
	public RolesDto() {
		super();
		// TODO Auto-generated constructor stub
	}


	public RolesDto(Long roleId, String roleName) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
	}
	
	
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}


	@Override
	public String toString() {
		return "RolesDto [roleId=" + roleId + ", roleName=" + roleName + "]";
	}
    
    
    
}
