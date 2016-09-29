package com.tf.usermanagement.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the CHARGE_DETAIL_ACTIVE database view
 * 
 * @author Arvind Rao
 * 
 */
@Embeddable
public class ChargeDetailActivePK implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long chargeId;
	private Long organizationId;
	private Long languageId;

	public ChargeDetailActivePK() {
	}

	@Column(name="CHARGE_ID")
	public Long getChargeId() {
		return chargeId;
	}

	public void setChargeId(Long chargeId) {
		this.chargeId = chargeId;
	}

	@Column(name="ORGANIZATION_ID")
	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	@Column(name="LANGUAGE_ID")
	public Long getLanguageId() {
		return languageId;
	}

	public void setLanguageId(Long languageId) {
		this.languageId = languageId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ChargeDetailActivePK)) {
			return false;
		}
		ChargeDetailActivePK castOther = (ChargeDetailActivePK) other;
		return (this.chargeId.equals(castOther.chargeId))
				&& (this.organizationId.equals(castOther.organizationId)
						&& (this.languageId.equals(castOther.languageId)));

	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.chargeId ^ (this.chargeId >>> 32)));
		hash = hash * prime + ((int) (this.organizationId ^ (this.organizationId >>> 32)));
		hash = hash * prime + ((int) (this.languageId ^ (this.languageId >>> 32)));
		return hash;
	}

	
}