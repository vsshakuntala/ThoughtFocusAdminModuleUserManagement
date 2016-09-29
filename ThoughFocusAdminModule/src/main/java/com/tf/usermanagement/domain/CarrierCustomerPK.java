package com.tf.usermanagement.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the CARRIER_CUSTOMER_MAP database table.
 * 
 * @author Manaswita Mishra
 * 
 */
@Embeddable
public class CarrierCustomerPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long carrierId;
	private Long customerId;

	public CarrierCustomerPK() {
	}


	@Column(name = "CARRIER_ID")
	public Long getCarrierId() {
		return carrierId;
	}

	public void setCarrierId(Long carrierId) {
		this.carrierId = carrierId;
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
		if (!(other instanceof RolePermissionPK)) {
			return false;
		}
		CarrierCustomerPK castOther = (CarrierCustomerPK) other;
		return (this.carrierId.equals(castOther.carrierId))
				&& (this.customerId.equals(castOther.customerId));

	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.carrierId ^ (this.carrierId >>> 32)));
		hash = hash * prime
				+ ((int) (this.customerId ^ (this.customerId >>> 32)));

		return hash;
	}

	@Override
	public String toString() {
		return "BillToShipToPK [carrierId=" + carrierId + ", customerId="
				+ customerId + "]";
	}
	
}