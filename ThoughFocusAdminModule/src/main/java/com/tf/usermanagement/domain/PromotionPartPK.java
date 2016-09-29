package com.tf.usermanagement.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PromotionPartPK implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long partId;
	private Long promotionId;
	
	@Column(name = "PROMOTION_ID")
	public Long getPromotionId() {
		return promotionId;
	}
	@Column(name = "PART_ID")
	public Long getPartId() {
		return partId;
	}

	public void setPartId(Long partId) {
		this.partId = partId;
	}

	public void setPromotionId(Long promotionId) {
		this.promotionId = promotionId;
	}
	
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.promotionId ^ (this.promotionId >>> 32)));
		hash = hash * prime
				+ ((int) (this.partId ^ (this.partId >>> 32)));

		return hash;
	}

	@Override
	public boolean equals(Object obj){
		boolean result=false;
		if (obj instanceof PromotionPartPK){
			PromotionPartPK cat=(PromotionPartPK)obj;
			result = (cat.getPartId().equals(this.partId));
		}
		return result;
	}
	
	@Override
	public String toString() {
		return "PromotionPartPK [roleId=" + promotionId + ", permissionId="
				+ partId + "]";
	}

}
