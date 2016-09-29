package com.tf.usermanagement.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The composite key class for the GROUP_CUSTOMER database table.
 * 
 * @author Manaswita Mishra
 * 
 */
@Embeddable
public class GroupCustomerPK implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long groupId;
	private Long customerId;

	public GroupCustomerPK() {
	}

	@Column(name = "GROUP_ID")
	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	@Column(name = "CUSTOMER_ID")
	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof GroupHierarchyPK)) {
			return false;
		}
		GroupCustomerPK castOther = (GroupCustomerPK) other;
		return (this.groupId.equals(castOther.groupId))
				&& (this.customerId.equals(castOther.customerId));

	}
	
	
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.groupId ^ (this.groupId >>> 32)));
		hash = hash * prime
				+ ((int) (this.customerId ^ (this.customerId >>> 32)));

		return hash;
	}

	@Override
	public String toString() {
		return "UserRolePK [groupId=" + groupId + ", customerId=" + customerId + "]";
	}
	
}