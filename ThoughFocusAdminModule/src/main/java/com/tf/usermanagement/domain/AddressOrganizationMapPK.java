package com.tf.usermanagement.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The composite key class for the OrganizationAddress database table.
 * 
 * @author Lakhan Jain
 * 
 */
@Embeddable
public class AddressOrganizationMapPK implements Serializable {
private static final long serialVersionUID = 1L;
	
	private Integer organizationId;
	private Integer addressId;

	public AddressOrganizationMapPK() {
	}
	
	public AddressOrganizationMapPK(Integer organizationId, Integer addressId) {
		super();
		this.organizationId = organizationId;
		this.addressId = addressId;
	}

	@Column(name = "ORGANIZATION_ID")
	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	
	@Column(name = "ADDRESS_ID")
	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AddressOrganizationMapPK)) {
			return false;
		}
		AddressOrganizationMapPK castOther = (AddressOrganizationMapPK) other;
		return (this.organizationId.equals(castOther.organizationId))
				&& (this.addressId.equals(castOther.addressId));

	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.organizationId ^ (this.organizationId >>> 32)));
		/*hash = hash * prime
				+ ((int) (this.configName ^ (this.configName >>> 32)));*/

		return hash;
	}

	@Override
	public String toString() {
		return "OrganizationAddressPK [OrganizationId=" + organizationId + ", AddressId=" + addressId + "]";
	}
}
