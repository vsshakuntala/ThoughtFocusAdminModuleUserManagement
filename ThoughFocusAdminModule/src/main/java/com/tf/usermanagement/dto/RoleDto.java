package com.tf.usermanagement.dto;

/**
 * 
 * @author Rajendra
 *
 */
public class RoleDto {

	public RoleDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	private Long roleId;
	private String roleName;

	

	public RoleDto(Long roleId, String roleName) {
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
		return "RoleDto [roleId=" + roleId + ", roleName=" + roleName + "]";
	}

}
