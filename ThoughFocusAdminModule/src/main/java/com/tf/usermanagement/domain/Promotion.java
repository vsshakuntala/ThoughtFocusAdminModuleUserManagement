package com.tf.usermanagement.domain;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "PROMOTION")
public class Promotion implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long promotionId;
	private Date startDate;
	private Date endDate;
	private Media media;
	private Boolean active;
	private Timestamp createdDate;
	private Long createdBy;
	private Long modifiedBy;
	private Timestamp modifiedDate;
	private Long promotionTypeId;
	private Long discountTypeId;
	private Long discountValue;
	private Long displayTypeId;
	private Long organizationId;
	private PromotionDescription promotionDescription;
	private Set<PromotionPart> promotionPart;
	//private Set<PromotionCustomer> promotion;
	private PromotionType promotionType;
	private PromotionDiscountType promotionDiscountType;
	private PromotionDisplayType promotionDisplayType;
	private String navigateUrl;
	private Long navigateTypeId;
	private Set<PromotionOrganizationMap> promotionOrganizationMap;

	// private Set<PromotionGroup> promotionGroup;

	public Promotion() {
		super();
	}

/*	public Promotion(Long promotionId, Date startDate, Date endDate,
			Media media, Boolean active, Timestamp createdDate, Long createdBy,
			Long modifiedBy, Timestamp modifiedDate, Long promotionTypeId,
			Long discountTypeId, Long discountValue, Long displayTypeId,
			Long organizationId, PromotionDescription promotionDescription,
			Set<PromotionPart> promotionPart, Set<PromotionCustomer> promotion,
			PromotionType promotionType,
			PromotionDiscountType promotionDiscountType,
			PromotionDisplayType promotionDisplayType, String navigateUrl,
			Long navigateTypeId) {
		super();
		this.promotionId = promotionId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.media = media;
		this.active = active;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.promotionTypeId = promotionTypeId;
		this.discountTypeId = discountTypeId;
		this.discountValue = discountValue;
		this.displayTypeId = displayTypeId;
		this.organizationId = organizationId;
		this.promotionDescription = promotionDescription;
		this.promotionPart = promotionPart;
		this.promotion = promotion;
		this.promotionType = promotionType;
		this.promotionDiscountType = promotionDiscountType;
		this.promotionDisplayType = promotionDisplayType;
		this.navigateUrl = navigateUrl;
		this.navigateTypeId = navigateTypeId;
	}*/

	public Promotion(Long promotionId, 
			Long navigateTypeId, String navigateUrl,
			Long mediaMediaId,Long promotionTypeId,String promotionTypeRankOrder,String promotionTypePromotionTypeDesc,Long discountValue,
			Long promotionDiscountTypeDiscountTypeId,String promotionDiscountTypeDiscountTypeDescription,
			Long promotionDisplayTypedisplayTypeId,String promotionDescriptionPromotionName,
			String promotionDescriptionDescription,Long organizationId) {
		this.promotionId = promotionId;
		this.navigateUrl = navigateUrl;
		this.navigateTypeId = navigateTypeId;
		if (promotionDescriptionPromotionName != null) {
			PromotionDescription promotionDescription = new PromotionDescription();
			promotionDescription.setPromotionName(promotionDescriptionPromotionName);
			promotionDescription.setDescription(promotionDescriptionDescription);
			this.promotionDescription = promotionDescription;
		}
		if (mediaMediaId != null) {
			Media media = new Media();
			media.setMediaId(mediaMediaId);
			this.media = media;
		}
		
		this.promotionTypeId = promotionTypeId;
		
		if (promotionTypeRankOrder != null) {
			PromotionType promotionType = new PromotionType();
			promotionType.setRankOrder(promotionTypeRankOrder);
			promotionType.setPromotionTypeDesc(promotionTypePromotionTypeDesc);
			this.promotionType = promotionType;
		}
		this.discountValue = discountValue;
		
		if (promotionDiscountTypeDiscountTypeId != null && promotionDiscountTypeDiscountTypeDescription !=null){
			PromotionDiscountType promotionDiscountType = new PromotionDiscountType();
			promotionDiscountType.setDiscountTypeId(promotionDiscountTypeDiscountTypeId);
			promotionDiscountType.setDiscountTypeDescription(promotionDiscountTypeDiscountTypeDescription);
			this.promotionDiscountType = promotionDiscountType;
		}
		
		if (promotionDisplayTypedisplayTypeId != null){
			PromotionDisplayType promotionDisplayType = new PromotionDisplayType();
			promotionDisplayType.setDisplayTypeId(promotionDisplayTypedisplayTypeId);
			this.promotionDisplayType = promotionDisplayType;
		}
		
		this.organizationId = organizationId;
	}

	@Id
	@GeneratedValue
	@Column(name = "PROMOTION_ID")
	public Long getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(Long promotionId) {
		this.promotionId = promotionId;
	}

	@Column(name = "START_DATE")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Column(name = "END_DATE")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "ACTIVE")
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
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

	@Column(name = "PROMOTION_TYPE_ID")
	public Long getPromotionTypeId() {
		return promotionTypeId;
	}

	public void setPromotionTypeId(Long promotionTypeId) {
		this.promotionTypeId = promotionTypeId;
	}

	@Column(name = "DISCOUNT_TYPE_ID")
	public Long getDiscountTypeId() {
		return discountTypeId;
	}

	public void setDiscountTypeId(Long discountTypeId) {
		this.discountTypeId = discountTypeId;
	}

	@Column(name = "DISCOUNT_VALUE")
	public Long getDiscountValue() {
		return discountValue;
	}

	public void setDiscountValue(Long discountValue) {
		this.discountValue = discountValue;
	}

	@OneToMany(mappedBy = "promotion", fetch = FetchType.LAZY)
	public Set<PromotionPart> getPromotionPart() {
		return promotionPart;
	}

	public void setPromotionPart(Set<PromotionPart> promotionPart) {
		this.promotionPart = promotionPart;
	}

	@OneToOne
	@JoinColumn(name = "MEDIA_ID")
	public Media getMedia() {
		return media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

	@Column(name = "PROMOTION_DISPLAY_TYPE_ID")
	public Long getDisplayTypeId() {
		return displayTypeId;
	}

	public void setDisplayTypeId(Long displayTypeId) {
		this.displayTypeId = displayTypeId;
	}

	@Column(name = "ORGANIZATION_ID")
	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}
	@Column(name ="NAVIGATE_URL")
	public String getNavigateUrl() {
		return navigateUrl;
	}

	public void setNavigateUrl(String navigateUrl) {
		this.navigateUrl = navigateUrl;
	}
	@Column(name ="NAVIGATE_TYPE")
	public Long getNavigateTypeId() {
		return navigateTypeId;
	}

	public void setNavigateTypeId(Long navigateTypeId) {
		this.navigateTypeId = navigateTypeId;
	}

	@OneToOne
	@JoinColumn(name = "PROMOTION_TYPE_ID", insertable = false, updatable = false)
	public PromotionType getPromotionType() {
		return promotionType;
	}

	public void setPromotionType(PromotionType promotionType) {
		this.promotionType = promotionType;
	}

	@OneToOne
	@JoinColumn(name = "DISCOUNT_TYPE_ID", insertable = false, updatable = false)
	public PromotionDiscountType getPromotionDiscountType() {
		return promotionDiscountType;
	}

	public void setPromotionDiscountType(
			PromotionDiscountType promotionDiscountType) {
		this.promotionDiscountType = promotionDiscountType;
	}

	@OneToOne
	@JoinColumn(name = "PROMOTION_DISPLAY_TYPE_ID", insertable = false, updatable = false)
	public PromotionDisplayType getPromotionDisplayType() {
		return promotionDisplayType;
	}

	public void setPromotionDisplayType(
			PromotionDisplayType promotionDisplayType) {
		this.promotionDisplayType = promotionDisplayType;
	}

	@OneToOne(mappedBy = "promotion", fetch = FetchType.EAGER)
	public PromotionDescription getPromotionDescription() {
		return promotionDescription;
	}

	public void setPromotionDescription(
			PromotionDescription promotionDescription) {
		this.promotionDescription = promotionDescription;
	}
 
	@OneToMany(mappedBy="promotion",fetch=FetchType.LAZY)
	public Set<PromotionOrganizationMap> getPromotionOrganizationMap() {
		return promotionOrganizationMap;
	}

	public void setPromotionOrganizationMap(
			Set<PromotionOrganizationMap> promotionOrganizationMap) {
		this.promotionOrganizationMap = promotionOrganizationMap;
	}

	@Override
	public String toString() {
		return "Promotion [promotionId=" + promotionId + ", startDate="
				+ startDate + ", endDate=" + endDate + ",  active=" + active
				+ ", createdDate=" + createdDate + ", createdBy=" + createdBy
				+ ", modifiedBy=" + modifiedBy + ", modifiedDate="
				+ modifiedDate + ", promotionTypeId=" + promotionTypeId
				+ ", discountTypeId=" + discountTypeId + ", discountValue="
				+ discountValue + ", promotionDescription="
				+ promotionDescription + " ]";
	}

}