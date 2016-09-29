package com.tf.usermanagement.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PromotionOrganizationMapPK implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long promotionId;
	private Long organizationId;

	@Column(name = "PROMOTION_ID")
	public Long getPromotionId() {
		return promotionId;
	}
	public void setPromotionId(Long promotionId) {
		this.promotionId = promotionId;
	}

	@Column(name = "ORGANIZATION_ID")
	public Long getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((organizationId == null) ? 0 : organizationId.hashCode());
		result = prime * result
				+ ((promotionId == null) ? 0 : promotionId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PromotionOrganizationMapPK other = (PromotionOrganizationMapPK) obj;
		if (organizationId == null) {
			if (other.organizationId != null)
				return false;
		} else if (!organizationId.equals(other.organizationId))
			return false;
		if (promotionId == null) {
			if (other.promotionId != null)
				return false;
		} else if (!promotionId.equals(other.promotionId))
			return false;
		return true;
	}

  @Override
  public String toString(){
	  return "PromotionOrganizationMapPK [promotionId=" + promotionId + ", organizationId="
				+ organizationId + "]";
	  
  }
	



}
