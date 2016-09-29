package com.tf.usermanagement.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PromotionDescriptionPK implements Serializable {

	
	private static final long serialVersionUID = 1L;
	 @Column(name="PROMOTION_ID")
	 private Long promotionId;
	 @Column(name="LANGUAGE_ID")
	 private Long languageId;
	 
	public Long getPromotionId() {
		return promotionId;
	}
	public void setPromotionId(Long promotionId) {
		this.promotionId = promotionId;
	}
	public Long getLanguageId() {
		return languageId;
	}
	public void setLanguageId(Long languageId) {
		this.languageId = languageId;
	}
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.promotionId ^ (this.promotionId >>> 32)));
		hash = hash * prime
				+ ((int) (this.languageId ^ (this.languageId >>> 32)));

		return hash;
	}
	
	@Override
	public boolean equals(Object obj){
		boolean result=false;
		if (obj instanceof PromotionDescriptionPK){
			PromotionDescriptionPK cat=(PromotionDescriptionPK)obj;
			result = (cat.getPromotionId().equals(this.promotionId));
		}
		return result;
	}

	@Override
	public String toString() {
		return "PromotionDescriptionPK [groupId=" + promotionId + ", userId=" + languageId + "]";
	}
	 
}
