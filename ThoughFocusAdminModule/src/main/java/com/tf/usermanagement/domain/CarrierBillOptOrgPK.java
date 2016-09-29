package com.tf.usermanagement.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the CARRIER_BILL_OPT_ORG database table.
 * 
 * @author Arvind Rao
 * 
 */
@Embeddable
public class CarrierBillOptOrgPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Integer carrierBillingOptionId;
	private Integer organizationId;

	public CarrierBillOptOrgPK() {
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CarrierBillOptOrgPK)) {
			return false;
		}
		CarrierBillOptOrgPK castOther = (CarrierBillOptOrgPK) other;
		return (this.carrierBillingOptionId.equals(castOther.carrierBillingOptionId))
				&& (this.organizationId.equals(castOther.organizationId));

	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.carrierBillingOptionId ^ (this.carrierBillingOptionId >>> 32)));
		hash = hash * prime
				+ ((int) (this.organizationId ^ (this.organizationId >>> 32)));

		return hash;
	}

	@Column(name="ORGANIZATION_ID")
	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	@Column(name="CARRIER_BILLING_OPTION_ID")
	public Integer getCarrierBillingOptionId() {
		return carrierBillingOptionId;
	}

	public void setCarrierBillingOptionId(Integer carrierBillingOptionId) {
		this.carrierBillingOptionId = carrierBillingOptionId;
	}
}