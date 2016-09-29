package com.tf.usermanagement.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProductCategoryPartPK implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long productCategoryId;
	private Long partId;
	private Long productCatalogId;
	
	@Column(name="PRODUCT_CATEGORY_ID")
	public Long getProductCategoryId() {
		return productCategoryId;
	}
	public void setProductCategoryId(Long productCategoryId) {
		this.productCategoryId = productCategoryId;
	}
	
	@Column(name="PART_ID")
	public Long getPartId() {
		return partId;
	}
	public void setPartId(Long partId) {
		this.partId = partId;
	}
	
	@Column(name="PRODUCT_CATALOG_ID")
	public Long getProductCatalogId() {
		return productCatalogId;
	}
	public void setProductCatalogId(Long productCatalogId) {
		this.productCatalogId = productCatalogId;
	}
	
	
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ProductCatalogOrganizationPK)) {
			return false;
		}
		ProductCategoryPartPK castOther = (ProductCategoryPartPK)other;
		return 
			(this.productCatalogId == castOther.productCatalogId)
			&& (this.productCategoryId == castOther.productCategoryId)
			&& (this.partId == castOther.partId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.productCatalogId ^ (this.productCatalogId >>> 32)));
		hash = hash * prime + ((int) (this.productCategoryId ^ (this.productCategoryId >>> 32)));
		hash = hash * prime + ((int) (this.partId ^ (this.partId >>> 32)));
		
		return hash;
    }
	
}
