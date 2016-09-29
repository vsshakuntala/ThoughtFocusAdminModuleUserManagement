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
@Table(name = "PROMOTION_CUSTOMER")
public class PromotionCustomer implements Serializable {

	public PromotionCustomer() {
		super();
		 
	}
 
	private static final long serialVersionUID = 6474871494694942989L;
 
	@EmbeddedId
	private PromotionCustomerPK id;
	
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
	private Promotion promotion;
	
	@Column(name = "CUSTOMER_ID", insertable=false, updatable=false)
	private Long customerId;
	
	public PromotionCustomer(PromotionCustomerPK id, Timestamp createdDate,
			Long createdBy, Timestamp modifiedDate, Long modifiedBy,
			boolean active, Promotion promotion) {
		super();
		this.id = id;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.modifiedDate = modifiedDate;
		this.modifiedBy = modifiedBy;
		this.active = active;
		this.promotion = promotion;
	}


//	public Long getPromotionId() {
//		return promotionId;
//	}
//
//	public void setPromotionId(Long promotionId) {
//		this.promotionId = promotionId;
//	}
//
//	public Long getCustomerId() {
//		return customerId;
//	}
//
//	public void setCustomerId(Long customerId) {
//		this.customerId = customerId;
//	}

	


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

	public PromotionCustomerPK getId() {
		return id;
	}

	public void setId(PromotionCustomerPK id) {
		this.id = id;
	}


	public Promotion getCustomerPromotion() {
		// TODO Auto-generated method stub
		return null;
	}
	public Long getCustomerId() {
		return customerId;
	}


	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}


	public Promotion getPromotion() {
		return promotion;
	}


	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}

 
}
