package com.tf.usermanagement.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="PROMOTION_TYPE")
public class PromotionType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9198271971371623964L;
	
	@Id
	@GeneratedValue
	@Column(name="PROMOTION_TYPE_ID")
	private Long promotionTypeId;
	@Column(name="PROMOTION_TYPE_VAL")
	private String promotionTypeDesc;
	@Column(name="RANK_ORDER")
	private String rankOrder;
	
	public Long getPromotionTypeId() {
		return promotionTypeId;
	}
	public void setPromotionTypeId(Long promotionTypeId) {
		this.promotionTypeId = promotionTypeId;
	}
	public String getPromotionTypeDesc() {
		return promotionTypeDesc;
	}
	public void setPromotionTypeDesc(String promotionTypeDesc) {
		this.promotionTypeDesc = promotionTypeDesc;
	}
	
	public String getRankOrder() {
		return rankOrder;
	}
	public void setRankOrder(String rankOrder) {
		this.rankOrder = rankOrder;
	}
	@Override
	public String toString() {
		return "PromotionType [promotionTypeId=" + promotionTypeId
				+ ", promotionTypeDesc=" + promotionTypeDesc + ", rankOrder="
				+ rankOrder + "]";
	}
	 

}
