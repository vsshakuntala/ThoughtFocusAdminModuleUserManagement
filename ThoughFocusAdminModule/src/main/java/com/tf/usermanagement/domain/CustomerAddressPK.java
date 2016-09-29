package com.tf.usermanagement.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the CUSTOMER_ADDRESS database table.
 * 
 * @author Arvind Rao
 * 
 */
@Embeddable
public class CustomerAddressPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long customerId;
	private Long addressId;

	public CustomerAddressPK() {
	}

	@Column(name = "CUSTOMER_ID")
	public Long getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
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
		if (!(other instanceof CustomerAddressPK)) {
			return false;
		}
		CustomerAddressPK castOther = (CustomerAddressPK) other;
		return (this.customerId.equals(castOther.customerId))
				&& (this.addressId.equals(castOther.addressId));

	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.customerId ^ (this.customerId >>> 32)));
		hash = hash * prime
				+ ((int) (this.addressId ^ (this.addressId >>> 32)));

		return hash;
	}

	@Override
	public String toString() {
		return "CustomerAddressPK [customerId=" + customerId + ", addressId=" + addressId
				+ "]";
	}
	
	
}