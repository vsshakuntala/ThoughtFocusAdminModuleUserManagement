/**
 * 
 */
package com.tf.usermanagement.domain;

import java.io.Serializable;

import javax.persistence.Column;

/**
 * @author HP430
 *
 */
public class AdminProductCategoruHierarchyPK implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long productCategoryId;
	private Long productCatalogId;
	
	@Column(name="PRODUCT_CATEGORY_ID")
	public Long getProductCategoryId() {
		return productCategoryId;
	}
	public void setProductCategoryId(Long productCategoryId) {
		this.productCategoryId = productCategoryId;
	}
	
		
	
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.productCategoryId ^ (this.productCategoryId >>> 32)));
		hash = hash * prime
				+ ((int) (this.productCatalogId ^ (this.productCatalogId >>> 32)));

		return hash;
	}
	
	@Column(name="PRODUCT_CATALOG_ID")
	public Long getProductCatalogId() {
		return productCatalogId;
	}
	public void setProductCatalogId(Long productCatalogId) {
		this.productCatalogId = productCatalogId;
	}
	
}
