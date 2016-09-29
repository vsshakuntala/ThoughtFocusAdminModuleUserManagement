package com.tf.usermanagement.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProductCatalogDescriptionPK implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long productCatalogId;
	private Long languageId;
	
	@Column(name="PRODUCT_CATALOG_ID")
	public Long getProductCatalogId() {
		return productCatalogId;
	}
	public void setProductCatalogId(Long productCatalogId) {
		this.productCatalogId = productCatalogId;
	}
	
	@Column(name="LANGUAGE_ID")
	public Long getLanguageId() {
		return languageId;
	}
	public void setLanguageId(Long languageId) {
		this.languageId = languageId;
	}
	
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.productCatalogId ^ (this.productCatalogId >>> 32)));
		hash = hash * prime
				+ ((int) (this.languageId ^ (this.languageId >>> 32)));

		return hash;
	}

	
	@Override
	public boolean equals(Object obj){
		boolean result=false;
		if (obj instanceof ProductCatalogDescriptionPK){
			ProductCatalogDescriptionPK cat=(ProductCatalogDescriptionPK)obj;
			result = (cat.getProductCatalogId().equals(this.productCatalogId));
		}
		return result;
	}
	
	@Override
	public String toString() {
		return "ProductCatalogDescriptionPK [productCatalogId=" + productCatalogId + ", languageId=" + languageId + "]";
	}

}
