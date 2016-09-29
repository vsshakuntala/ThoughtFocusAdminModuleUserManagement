package com.tf.usermanagement.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the CARRIER_ORGANIZATION database table.
 * 
 * @author Arvind Rao
 * 
 */
@Embeddable
public class CarrierOrganizationPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Integer carrierId;
	private Integer organizationId;

	public CarrierOrganizationPK() {
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CarrierOrganizationPK)) {
			return false;
		}
		CarrierOrganizationPK castOther = (CarrierOrganizationPK) other;
		return (this.carrierId.equals(castOther.carrierId))
				&& (this.organizationId.equals(castOther.organizationId));

	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.carrierId ^ (this.carrierId >>> 32)));
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

	@Column(name="CARRIER_ID")
	public Integer getCarrierId() {
		return carrierId;
	}

	public void setCarrierId(Integer carrierId) {
		this.carrierId = carrierId;
	}
}