package com.tf.usermanagement.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the UPSELL database table.
 * 
 * @author Sai Prasad
 * 
 */
@Embeddable
public class UpsellPartPK implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long partId;
	private Long upsellPartId;

	@Column(name = "PART_ID")
	public Long getPartId() {
		return partId;
	}

	public void setPartId(Long partId) {
		this.partId = partId;
	}

	@Column(name = "UPSELL_PART_ID")
	public Long getUpsellPartId() {
		return upsellPartId;
	}

	public void setUpsellPartId(Long upsellPartId) {
		this.upsellPartId = upsellPartId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((partId == null) ? 0 : partId.hashCode());
		result = prime * result
				+ ((upsellPartId == null) ? 0 : upsellPartId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UpsellPartPK other = (UpsellPartPK) obj;
		if (partId == null) {
			if (other.partId != null)
				return false;
		} else if (!partId.equals(other.partId))
			return false;
		if (upsellPartId == null) {
			if (other.upsellPartId != null)
				return false;
		} else if (!upsellPartId.equals(other.upsellPartId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UpsellPartPK [partId=" + partId + ", upsellPartId="
				+ upsellPartId + "]";
	}

	 
}
