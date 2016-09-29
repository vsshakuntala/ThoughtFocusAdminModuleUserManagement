package com.tf.usermanagement.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The composite key class for the GROUP_HIERARCHY database table.
 * 
 * @author Manaswita Mishra
 * 
 */
@Embeddable
public class GroupHierarchyPK implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long groupId;
	private Long parentGroupId;

	public GroupHierarchyPK() {
	}

	@Column(name = "GROUP_ID")
	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	@Column(name = "PARENT_GROUP_ID")
	public Long getParentGroupId() {
		return parentGroupId;
	}

	public void setParentGroupId(Long parentGroupId) {
		this.parentGroupId = parentGroupId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof GroupHierarchyPK)) {
			return false;
		}
		GroupHierarchyPK castOther = (GroupHierarchyPK) other;
		return (this.groupId.equals(castOther.groupId))
				&& (this.parentGroupId.equals(castOther.parentGroupId));

	}
	
	
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.groupId ^ (this.groupId >>> 32)));
		hash = hash * prime
				+ ((int) (this.parentGroupId ^ (this.parentGroupId >>> 32)));

		return hash;
	}

	@Override
	public String toString() {
		return "GroupHierarchyPK [groupId=" + groupId + ", parentGroupId=" + parentGroupId + "]";
	}
	
}