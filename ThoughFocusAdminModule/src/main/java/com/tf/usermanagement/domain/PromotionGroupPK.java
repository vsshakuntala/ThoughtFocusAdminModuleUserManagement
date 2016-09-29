package com.tf.usermanagement.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
@Embeddable
public class PromotionGroupPK implements Serializable{
 
	private static final long serialVersionUID = 1L;
	@Column(name="PROMOTION_ID")
	private Long promotionId;
	@Column(name="GROUP_ID")
	private Long groupId;
	
	public Long getPromotionId() {
		return promotionId;
	}
	public void setPromotionId(Long promotionId) {
		this.promotionId = promotionId;
	}
	
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.promotionId ^ (this.promotionId >>> 32)));
		hash = hash * prime
				+ ((int) (this.groupId ^ (this.groupId >>> 32)));

		return hash;
	}

	
	@Override
	public boolean equals(Object obj){
		boolean result=false;
		if (obj instanceof PromotionGroupPK){
			PromotionGroupPK cat=(PromotionGroupPK)obj;
			result = (cat.getPromotionId().equals(this.promotionId));
		}
		return result;
	}
	
	@Override
	public String toString() {
		return "PromotionGroupPK [promotionId=" + promotionId + ", groupId=" + groupId + "]";
	}

}
