package com.tf.usermanagement.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the CARRIER_BILL_OPT_CUSTOMER_MAP database table.
 * 
 * @author Arvind Rao
 * 
 */
@Embeddable
public class CarrierBillOptCustomerMapPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Integer carrierBillingOptionId;
	private Long customerId;

	public CarrierBillOptCustomerMapPK() {
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CarrierBillOptCustomerMapPK)) {
			return false;
		}
		CarrierBillOptCustomerMapPK castOther = (CarrierBillOptCustomerMapPK) other;
		return (this.carrierBillingOptionId.equals(castOther.carrierBillingOptionId))
				&& (this.customerId.equals(castOther.customerId));

	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.carrierBillingOptionId ^ (this.carrierBillingOptionId >>> 32)));
		hash = hash * prime
				+ ((int) (this.customerId ^ (this.customerId >>> 32)));

		return hash;
	}

	@Column(name="CARRIER_BILLING_OPTION_ID")
	public Integer getCarrierBillingOptionId() {
		return carrierBillingOptionId;
	}

	public void setCarrierBillingOptionId(Integer carrierBillingOptionId) {
		this.carrierBillingOptionId = carrierBillingOptionId;
	}

	@Column(name="CUSTOMER_ID")
	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
}