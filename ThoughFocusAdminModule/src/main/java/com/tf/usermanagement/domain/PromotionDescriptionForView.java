package com.tf.usermanagement.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "VW_PROMOTION_DESCRIPTION")
 
public class PromotionDescriptionForView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	
	@EmbeddedId
	private PromotionDescriptionPK id;

	@Column(name = "NAME")
	private String promotionName;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "ACTIVE")
	private Boolean active;
	
	@Column(name = "CREATED_BY")
	private Long createdBy;
	
	@Column(name = "CREATED_DATE")
	private Timestamp createdDate;
	
	@Column(name = "MODIFIED_DATE")
	private Timestamp modifiedDate;
	
	@Column(name = "MODIFIED_BY")
	private Long modifiedBy;
	
	@Column(name = "LANGUAGE_ID", insertable=false, updatable=false)
	private Long languageId;

    @OneToOne
	@JoinColumn(name="PROMOTION_ID" , insertable=false,updatable=false)
	private Promotion promotion;
	
	public Promotion getPromotion() {
		return promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}

 	public String getPromotionName() {
		return promotionName;
	}

	public PromotionDescriptionPK getId() {
		return id;
	}

	public void setId(PromotionDescriptionPK id) {
		this.id = id;
	}

	public void setPromotionName(String promotionName) {
		this.promotionName = promotionName;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getLanguageId() {
		return languageId;
	}

	public void setLanguageId(Long languageId) {
		this.languageId = languageId;
	}
 
}
