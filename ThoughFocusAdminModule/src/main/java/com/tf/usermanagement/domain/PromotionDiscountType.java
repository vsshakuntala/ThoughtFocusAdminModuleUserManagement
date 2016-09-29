package com.tf.usermanagement.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="PROMOTION_DISCOUNT_TYPE")
public class PromotionDiscountType implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7775278058382179898L;
	
	private Long discountTypeId;
	private String discountTypeDescription;
	
	@Id
	@Column(name="DISCOUNT_TYPE_ID")
	public Long getDiscountTypeId() {
		return discountTypeId;
	}
	public void setDiscountTypeId(Long discountTypeId) {
		this.discountTypeId = discountTypeId;
	}
	
	@Column(name="DISCOUNT_DESC")
	public String getDiscountTypeDescription() {
		return discountTypeDescription;
	}
	public void setDiscountTypeDescription(String discountTypeDescription) {
		this.discountTypeDescription = discountTypeDescription;
	}
	@Override
	public String toString() {
		return "PromotionDiscountType [discountTypeDescription="
				+ discountTypeDescription + ", discountTypeId="
				+ discountTypeId + "]";
	}
	
}
