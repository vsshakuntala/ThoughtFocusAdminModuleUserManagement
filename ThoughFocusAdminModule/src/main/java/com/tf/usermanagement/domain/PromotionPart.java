package com.tf.usermanagement.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PROMOTION_PART")
public class PromotionPart implements Serializable {

	private static final long serialVersionUID = -8960998583009653827L;

	private PromotionPartPK promotionpartPK;

	private Boolean active;

	private Long createdBy;

	private Timestamp createdDate;

	private Timestamp modifiedDate;

	private Long modifiedBy;

	private Part part;
	
	private Promotion promotion;
	
	private Long organizationId;
	
	@EmbeddedId
	public PromotionPartPK getPromotionpartPK() {
		return promotionpartPK;
	}

	public void setPromotionpartPK(PromotionPartPK promotionpartPK) {
		this.promotionpartPK = promotionpartPK;
	}

	@Column(name = "ACTIVE")
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Column(name = "CREATED_BY")
	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "CREATED_DATE")
	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "MODIFIED_DATE")
	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Column(name = "MODIFIED_BY")
	public Long getModifiedBy() {
		return modifiedBy;
	}
	
	@Column(name = "ORGANIZATION_ID")
	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROMOTION_ID", insertable = false, updatable = false)
	public Promotion getPromotion() {
		return promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}

	@OneToOne
	@JoinColumn(name = "PART_ID",insertable=false,updatable=false)
	public Part getPart() {
		return part;
	}

	public void setPart(Part part) {
		this.part = part;
	}

	@Override
	public String toString() {
		return "PromotionPart [promotionpartPK=" + promotionpartPK
				+ ", active=" + active + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", modifiedDate="
				+ modifiedDate + ", modifiedBy=" + modifiedBy + ", part="
				+ part + ", promotion=" + promotion + "]";
	}
	
	
	
}
