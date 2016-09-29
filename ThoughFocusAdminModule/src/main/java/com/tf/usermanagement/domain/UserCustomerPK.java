package com.tf.usermanagement.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the USER_CUSTOMER database table.
 * 
 * @author Arvind.C
 * 
 */
@Embeddable
public class UserCustomerPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long userId;
	private Long customerId;

	public UserCustomerPK() {
	}

	@Column(name = "USER_ID")
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "CUSTOMER_ID")
	public Long getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UserCustomerPK)) {
			return false;
		}
		UserCustomerPK castOther = (UserCustomerPK) other;
		return (this.userId.equals(castOther.userId))
				&& (this.customerId.equals(castOther.customerId));

	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.userId ^ (this.userId >>> 32)));
		hash = hash * prime
				+ ((int) (this.customerId ^ (this.customerId >>> 32)));

		return hash;
	}

	@Override
	public String toString() {
		return "UserCustomerPK [userId=" + userId + ", customerId="
				+ customerId + "]";
	}
	
}