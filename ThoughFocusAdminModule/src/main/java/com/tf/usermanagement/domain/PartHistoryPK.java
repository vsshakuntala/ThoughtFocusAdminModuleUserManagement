package com.tf.usermanagement.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * The primary key class for the PART_HISTORY database table.
 * 
 */
@Embeddable
public class PartHistoryPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private Long partId;

	private Long substitutePartId;

	private Date effectiveFromDate;

	private Boolean active;

	public PartHistoryPK() {
	}

	@Column(name = "PART_ID")
	public Long getPartId() {
		return this.partId;
	}

	public void setPartId(Long partId) {
		this.partId = partId;
	}

	@Column(name = "SUBSTITUTE_PART_ID")
	public Long getSubstitutePartId() {
		return this.substitutePartId;
	}

	public void setSubstitutePartId(Long substitutePartId) {
		this.substitutePartId = substitutePartId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EFFECTIVE_FROM_DATE")
	public java.util.Date getEffectiveFromDate() {
		return this.effectiveFromDate;
	}

	public void setEffectiveFromDate(java.util.Date effectiveFromDate) {
		this.effectiveFromDate = effectiveFromDate;
	}

	@Column(name = "ACTIVE")
	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PartHistoryPK)) {
			return false;
		}
		PartHistoryPK castOther = (PartHistoryPK) other;
		return (this.partId == castOther.partId)
				&& (this.substitutePartId == castOther.substitutePartId)
				&& this.effectiveFromDate.equals(castOther.effectiveFromDate)
				&& (this.active == castOther.active);

	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.partId ^ (this.partId >>> 32)));
		hash = hash
				* prime
				+ ((int) (this.substitutePartId ^ (this.substitutePartId >>> 32)));
		hash = hash * prime + this.effectiveFromDate.hashCode();
		hash = hash * prime + (this.active ? 1 : 0);

		return hash;
	}
}