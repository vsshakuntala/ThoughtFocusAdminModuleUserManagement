package com.tf.usermanagement.domain;

import java.io.Serializable;
import java.sql.Timestamp;

 
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
 
import javax.persistence.Table;

@Entity
@Table(name = "PROMOTION_GROUP")
public class PromotionGroup implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6722074587908238378L;
	
    @EmbeddedId
	private PromotionGroupPK promotionGroupPK;

	@Column(name = "CREATED_DATE")
	private Timestamp createdDate;

	@Column(name = "CREATED_BY")
	private Long createdBy;

	@Column(name = "MODIFIED_DATE")
	private Timestamp modifiedDate;

	@Column(name = "MODIFIED_BY")
	private Long modifiedBy;

	@Column(name = "ACTIVE")
	private boolean active;
 
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "PROMOTION_ID", insertable=false, updatable=false)
	private Promotion groupPromotion; 

	public Promotion getGroupPromotion() {
		return groupPromotion;
	}

	public void setGroupPromotion(Promotion groupPromotion) {
		this.groupPromotion = groupPromotion;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public PromotionGroupPK getPromotionGroupPK() {
		return promotionGroupPK;
	}

	public void setPromotionGroupPK(PromotionGroupPK promotionGroupPK) {
		this.promotionGroupPK = promotionGroupPK;
	}
 
	
	
	
}
