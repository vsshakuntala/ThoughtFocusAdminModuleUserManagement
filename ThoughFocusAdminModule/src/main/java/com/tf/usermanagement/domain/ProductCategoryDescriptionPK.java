package com.tf.usermanagement.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProductCategoryDescriptionPK implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long productCategoryId;
	private Long languageId;
	
	@Column(name="PRODUCT_CATEGORY_ID")
	public Long getProductCategoryId() {
		return productCategoryId;
	}
	public void setProductCategoryId(Long productCategoryId) {
		this.productCategoryId = productCategoryId;
	}
	
	@Column(name="LANGUAGE_ID")
	public Long getLanguageId() {
		return languageId;
	}
	public void setLanguageId(Long languageId) {
		this.languageId = languageId;
	}
	
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ProductCategoryDescriptionPK)) {
			return false;
		}
		ProductCategoryDescriptionPK castOther = (ProductCategoryDescriptionPK) other;
		return (this.productCategoryId.equals(castOther.productCategoryId))
				&& (this.languageId.equals(castOther.languageId));

	}
	
	
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.productCategoryId ^ (this.productCategoryId >>> 32)));
		hash = hash * prime
				+ ((int) (this.languageId ^ (this.languageId >>> 32)));

		return hash;
	}
	
	

	@Override
	public String toString() {
		return "ProductCategoryDescriptionPK [partId=" + productCategoryId + ", languageId=" + languageId + "]";
	}
	
}
