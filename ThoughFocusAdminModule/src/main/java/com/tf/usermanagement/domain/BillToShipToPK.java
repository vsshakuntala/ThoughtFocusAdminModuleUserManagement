package com.tf.usermanagement.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the BILL_SHIP_ADDRESS_MAP database table.
 * 
 * @author Manaswita Mishra
 * 
 */
@Embeddable
public class BillToShipToPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long billToAddressId;
	private Long shipToAddressId;

	public BillToShipToPK() {
	}

	@Column(name = "BILL_TO_ADDRESS_ID")
	public Long getBillToAddressId() {
		return billToAddressId;
	}


	public void setBillToAddressId(Long billToAddressId) {
		this.billToAddressId = billToAddressId;
	}

	@Column(name = "SHIP_TO_ADDRESS_ID")
	public Long getShipToAddressId() {
		return shipToAddressId;
	}

	public void setShipToAddressId(Long shipToAddressId) {
		this.shipToAddressId = shipToAddressId;
	}


	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RolePermissionPK)) {
			return false;
		}
		BillToShipToPK castOther = (BillToShipToPK) other;
		return (this.billToAddressId.equals(castOther.billToAddressId))
				&& (this.shipToAddressId.equals(castOther.shipToAddressId));

	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.billToAddressId ^ (this.billToAddressId >>> 32)));
		hash = hash * prime
				+ ((int) (this.shipToAddressId ^ (this.shipToAddressId >>> 32)));

		return hash;
	}

	@Override
	public String toString() {
		return "BillToShipToPK [billToAddressId=" + billToAddressId + ", shipToAddressId="
				+ shipToAddressId + "]";
	}
	
}