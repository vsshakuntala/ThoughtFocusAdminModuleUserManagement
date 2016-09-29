package com.tf.usermanagement.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The composite key class for the GROUP_ADDRESS database table.
 * 
 * @author Manaswita Mishra
 * 
 */
@Embeddable
public class GroupAddressPK implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long groupId;
	private Long addressId;

	public GroupAddressPK() {
	}

	@Column(name = "GROUP_ID")
	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	@Column(name = "ADDRESS_ID")
	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof GroupHierarchyPK)) {
			return false;
		}
		GroupAddressPK castOther = (GroupAddressPK) other;
		return (this.groupId.equals(castOther.groupId))
				&& (this.addressId.equals(castOther.addressId));

	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.groupId ^ (this.groupId >>> 32)));
		hash = hash * prime
				+ ((int) (this.addressId ^ (this.addressId >>> 32)));

		return hash;
	}

	@Override
	public String toString() {
		return "GroupAddressPK [groupId=" + groupId + ", addressId=" + addressId + "]";
	}
	
}