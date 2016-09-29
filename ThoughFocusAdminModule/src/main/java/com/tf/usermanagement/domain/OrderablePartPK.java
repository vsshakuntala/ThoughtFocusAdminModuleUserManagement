package com.tf.usermanagement.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the ORDERABLE_PART database table.
 * 
 */
@Embeddable
public class OrderablePartPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private Long partId;

	private Long orderablePartId;
	
	

    public OrderablePartPK() {
    }
	
    @Column(name="PART_ID")
	public Long getPartId() {
		return this.partId;
	}
	public void setPartId(Long partId) {
		this.partId = partId;
	}

	@Column(name="ORDERABLE_PART_ID")
	public Long getOrderablePartId() {
		return this.orderablePartId;
	}
	public void setOrderablePartId(Long orderablePartId) {
		this.orderablePartId = orderablePartId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof OrderablePartPK)) {
			return false;
		}
		OrderablePartPK castOther = (OrderablePartPK)other;
		return 
			(this.partId.equals(castOther.partId))
			&& (this.orderablePartId.equals(castOther.orderablePartId));

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.partId ^ (this.partId >>> 32)));
		hash = hash * prime + ((int) (this.orderablePartId ^ (this.orderablePartId >>> 32)));
		
		return hash;
    }
}