package com.tf.usermanagement.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the LEGACY_PART database table.
 * 
 */
@Embeddable
public class LegacyPartPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private Long partId;
	private Long legacyTypeId;
	
    public LegacyPartPK() {
    }
	
    @Column(name="PART_ID")
	public Long getPartId() {
		return this.partId;
	}
	public void setPartId(Long partId) {
		this.partId = partId;
	}

	@Column(name="LEGACY_TYPE_ID")
	public Long getLegacyTypeId() {
		return legacyTypeId;
	}

	public void setLegacyTypeId(Long legacyTypeId) {
		this.legacyTypeId = legacyTypeId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof LegacyPartPK)) {
			return false;
		}
		LegacyPartPK castOther = (LegacyPartPK)other;
		return 
			(this.partId == castOther.partId)
			&& (this.legacyTypeId == castOther.legacyTypeId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.partId ^ (this.partId >>> 32)));
		hash = hash * prime + ((int) (this.legacyTypeId ^ (this.legacyTypeId >>> 32)));
		
		return hash;
    }
}