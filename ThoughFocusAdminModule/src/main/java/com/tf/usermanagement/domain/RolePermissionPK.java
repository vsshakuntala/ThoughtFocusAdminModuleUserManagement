package com.tf.usermanagement.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the ROLE_PERMISSION database table.
 * 
 * @author Arvind.C
 * 
 */
@Embeddable
public class RolePermissionPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long roleId;
	private Long permissionId;

	public RolePermissionPK() {
	}

	@Column(name = "ROLE_ID")
	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	@Column(name = "PERMISSION_ID")
	public Long getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}


	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RolePermissionPK)) {
			return false;
		}
		RolePermissionPK castOther = (RolePermissionPK) other;
		return (this.roleId.equals(castOther.roleId))
				&& (this.permissionId.equals(castOther.permissionId));

	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.roleId ^ (this.roleId >>> 32)));
		hash = hash * prime
				+ ((int) (this.permissionId ^ (this.permissionId >>> 32)));

		return hash;
	}

	@Override
	public String toString() {
		return "RolePermissionPK [roleId=" + roleId + ", permissionId="
				+ permissionId + "]";
	}
	
}