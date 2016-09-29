package com.tf.usermanagement.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the USER_ROLE database table.
 * 
 * @author Arvind.C
 * 
 */
@Embeddable
public class UserRolePK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long userId;
	private Long roleId;

	public UserRolePK() {
	}

	@Column(name = "USER_ID")
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "ROLE_ID")
	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UserRolePK)) {
			return false;
		}
		UserRolePK castOther = (UserRolePK) other;
		return (this.userId.equals(castOther.userId))
				&& (this.roleId.equals(castOther.roleId));

	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.userId ^ (this.userId >>> 32)));
		hash = hash * prime
				+ ((int) (this.roleId ^ (this.roleId >>> 32)));

		return hash;
	}

	@Override
	public String toString() {
		return "UserRolePK [userId=" + userId + ", roleId=" + roleId + "]";
	}
	
}