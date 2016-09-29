package com.tf.usermanagement.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
 

@Embeddable
public class PromotionCustomerPK implements Serializable {
    
 
	private static final long serialVersionUID = 1L;
    @Column(name="PROMOTION_ID")
	private Long promotionId;
    @Column(name="CUSTOMER_ID")
	private Long customerId;
    
	public Long getPromotionId() {
		return promotionId;
	}
	public void setPromotionId(Long promotionId) {
		this.promotionId = promotionId;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.promotionId ^ (this.promotionId >>> 32)));
		hash = hash * prime
				+ ((int) (this.customerId ^ (this.customerId >>> 32)));

		return hash;
	}

	
	
	@Override
	public boolean equals(Object obj){
		boolean result=false;
		if (obj instanceof PromotionCustomerPK){
			PromotionCustomerPK cat=(PromotionCustomerPK)obj;
			result = (cat.getPromotionId().equals(this.promotionId));
		}
		return result;
	}
	@Override
	public String toString() {
		return "PromotionCustomerPK [roleId=" + promotionId + ", permissionId="
				+ customerId + "]";
	}
}
