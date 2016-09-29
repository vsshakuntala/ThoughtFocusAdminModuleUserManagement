package com.tf.usermanagement.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The composite key class for the USER_GROUP database table.
 * 
 * @author Manaswita Mishra
 * 
 */
@Embeddable
public class UserGroupPK implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long groupId;
	private Long userId;

	public UserGroupPK() {
	}

	@Column(name = "GROUP_ID")
	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	@Column(name = "USER_ID")
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UserGroupPK)) {
			return false;
		}
		UserGroupPK castOther = (UserGroupPK) other;
		return (this.groupId.equals(castOther.groupId))
				&& (this.userId.equals(castOther.userId));

	}
	
	
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.groupId ^ (this.groupId >>> 32)));
		hash = hash * prime
				+ ((int) (this.userId ^ (this.userId >>> 32)));

		return hash;
	}

	@Override
	public String toString() {
		return "UserGroupPK [groupId=" + groupId + ", userId=" + userId + "]";
	}
	
}