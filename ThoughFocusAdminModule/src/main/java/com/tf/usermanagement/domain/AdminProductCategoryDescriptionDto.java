/**
 * 
 */
package com.tf.usermanagement.domain;

import java.sql.Timestamp;

/**
 * @author HP430
 *
 */
public class AdminProductCategoryDescriptionDto {

	private ProductCategoryDescriptionPK id;
	private String name;
	private String description;
	private Long createdBy;
	private Timestamp createdDate;
	private Long modifiedBy;
	private Timestamp modifiedDate;
	private Boolean active;
	private Long productCatalogId;
	private Long organizationId;
	private Long productCategeoryId;
	public ProductCategoryDescriptionPK getId() {
		return id;
	}
	public void setId(ProductCategoryDescriptionPK id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public Long getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Timestamp getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	
	public Long getProductCatalogId() {
		return productCatalogId;
	}
	public void setProductCatalogId(Long productCatalogId) {
		this.productCatalogId = productCatalogId;
	}
	public Long getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}
	public Long getProductCategeoryId() {
		return productCategeoryId;
	}
	public void setProductCategeoryId(Long productCategeoryId) {
		this.productCategeoryId = productCategeoryId;
	}
	@Override
	public String toString() {
		return "AdminProductCategoryDescriptionDto [id=" + id + ", name="
				+ name + ", description=" + description + ", createdBy="
				+ createdBy + ", createdDate=" + createdDate + ", modifiedBy="
				+ modifiedBy + ", modifiedDate=" + modifiedDate + ", active="
				+ active + ", productCatalogId=" + productCatalogId
				+ ", organizationId=" + organizationId
				+ ", productCategeoryId=" + productCategeoryId + "]";
	}
	
}
