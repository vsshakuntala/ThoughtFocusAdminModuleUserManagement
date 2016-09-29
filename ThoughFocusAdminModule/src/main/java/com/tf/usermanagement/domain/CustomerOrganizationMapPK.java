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
public class CustomerOrganizationMapPK implements Serializable {
private static final long serialVersionUID = 1L;
	
	private Long organizationId;
	private Long customerId;

	public CustomerOrganizationMapPK() {
	}
	
	public CustomerOrganizationMapPK(Long organizationId, Long customerId) {
		super();
		this.organizationId = organizationId;
		this.customerId = customerId;
	}

	@Column(name = "ORGANIZATION_ID")
	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
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
		if (!(other instanceof CustomerOrganizationMapPK)) {
			return false;
		}
		CustomerOrganizationMapPK castOther = (CustomerOrganizationMapPK) other;
		return (this.organizationId.equals(castOther.organizationId))
				&& (this.customerId.equals(castOther.customerId));

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
		return "OrganizationAddressPK [OrganizationId=" + organizationId + ", CustomerId=" + customerId + "]";
	}
}
