package com.tf.usermanagement.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the USER_ADDRESS database table.
 * 
 * @author Arvind Rao
 * 
 */
@Embeddable
public class UserAddressPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long userId;
	private Long addressId;

	public UserAddressPK() {
	}

	@Column(name = "USER_ID")
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "ADDRESS_ID")
	public Long getAddressId() {
		return this.addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UserAddressPK)) {
			return false;
		}
		UserAddressPK castOther = (UserAddressPK) other;
		return (this.userId.equals(castOther.userId))
				&& (this.addressId.equals(castOther.addressId));

	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.userId ^ (this.userId >>> 32)));
		hash = hash * prime
				+ ((int) (this.addressId ^ (this.addressId >>> 32)));

		return hash;
	}

	@Override
	public String toString() {
		return "UserAddressPK [userId=" + userId + ", addressId=" + addressId
				+ "]";
	}
	
	
}