package com.tf.usermanagement.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="PROMOTION_ORGANIZATION_MAP")
public class PromotionOrganizationMap implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private PromotionOrganizationMapPK id;
	private Timestamp createdDate;
	private Long createdBy;
	private Long modifiedBy;
	private Timestamp modifiedDate;
	private Boolean active;
	private Organization organization;
	private Promotion promotion;
	
	@EmbeddedId
	public PromotionOrganizationMapPK getId() {
		return id;
	}
	public void setId(PromotionOrganizationMapPK id) {
		this.id = id;
	}
	@Column(name = "CREATED_DATE")
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	
	@Column(name = "CREATED_BY")
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	
	@Column(name = "MODIFIED_BY")
	public Long getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	@Column(name = "MODIFIED_DATE")
	public Timestamp getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	@Column(name = "ACTIVE")
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	
	@ManyToOne
	@JoinColumn(name="ORGANIZATION_ID", insertable=false, updatable=false)
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	
	@ManyToOne
	@JoinColumn(name="PROMOTION_Id", insertable=false, updatable=false)
	public Promotion getPromotion() {
		return promotion;
	}
	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}
	
	
	
	
	


}
