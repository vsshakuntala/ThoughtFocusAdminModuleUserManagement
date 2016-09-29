package com.tf.usermanagement.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProductCatalogOrganizationPK implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long productCatalogId;
	private Long organizationId;
	
	@Column(name="PRODUCT_CATALOG_ID")
	public Long getProductCatalogId() {
		return productCatalogId;
	}
	public void setProductCatalogId(Long productCatalogId) {
		this.productCatalogId = productCatalogId;
	}
	
	@Column(name="ORGANIZATION_ID")
	public Long getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}
	
	
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ProductCatalogOrganizationPK)) {
			return false;
		}
		ProductCatalogOrganizationPK castOther = (ProductCatalogOrganizationPK)other;
		return 
			(this.productCatalogId == castOther.productCatalogId)
			&& (this.organizationId == castOther.organizationId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.productCatalogId ^ (this.productCatalogId >>> 32)));
		hash = hash * prime + ((int) (this.organizationId ^ (this.organizationId >>> 32)));
		
		return hash;
    }
	
}
