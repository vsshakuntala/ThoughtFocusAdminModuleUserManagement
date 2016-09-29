package com.tf.usermanagement.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT_CATEGORY_HIERARCHY")
public class ProductCategoryHierarchy implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long productCategoryHierarchyId;
	private Long productCategoryId;
	private Long parentProductCategoryId;
	private Long productCatalogId;
	private Long createdBy;
	private Timestamp createdDate;
	private Long modifiedBy;
	private Timestamp modifiedDate; 
	private Boolean active;
	
	private ProductCatalog productCatalog;
	private ProductCategory productCategory;
	private ProductCategory parentProductCategory;
	
	
	@Id
	@Column(name="PRODUCT_CATEGORY_HIERARCHY_ID")
/*	@SequenceGenerator(name = "ProductCategoryHierarchySequence", sequenceName = "SEQ_PRODUCTCATEGORYHRCHY")
	@GeneratedValue(generator="ProductCategoryHierarchySequence")*/
	@GeneratedValue
	public Long getProductCategoryHierarchyId() {
		return productCategoryHierarchyId;
	}
	public void setProductCategoryHierarchyId(Long productCategoryHierarchyId) {
		this.productCategoryHierarchyId = productCategoryHierarchyId;
	}
	
	@Column(name="PRODUCT_CATEGORY_ID", insertable=false, updatable=false)
	public Long getProductCategoryId() {
		return productCategoryId;
	}
	public void setProductCategoryId(Long productCategoryId) {
		this.productCategoryId = productCategoryId;
	}
	
	@Column(name="PARENT_PRODUCT_CATEGORY_ID", insertable=false, updatable=false)
	public Long getParentProductCategoryId() {
		return parentProductCategoryId;
	}
	public void setParentProductCategoryId(Long parentProductCategoryId) {
		this.parentProductCategoryId = parentProductCategoryId;
	}
	
	@Column(name="PRODUCT_CATALOG_ID", insertable=false, updatable=false)
	public Long getProductCatalogId() {
		return productCatalogId;
	}
	public void setProductCatalogId(Long productCatalogId) {
		this.productCatalogId = productCatalogId;
	}
	
	@Column(name="CREATED_BY")
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	
	@Column(name="CREATED_DATE")
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	
	@Column(name="MODIFIED_BY")
	public Long getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	@Column(name="MODIFIED_DATE")
	public Timestamp getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	@Column(name="ACTIVE")
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	
	@ManyToOne
	@JoinColumn(name = "PRODUCT_CATALOG_ID", insertable=false, updatable=false)
	public ProductCatalog getProductCatalog() {
		return productCatalog;
	}
	public void setProductCatalog(ProductCatalog productCatalog) {
		this.productCatalog = productCatalog;
	}
	
	@OneToOne
	@JoinColumn(name = "PRODUCT_CATEGORY_ID", insertable=false, updatable=false)
	public ProductCategory getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}
	
	@OneToOne
	@JoinColumn(name = "PARENT_PRODUCT_CATEGORY_ID", insertable=false, updatable=false)
	public ProductCategory getParentProductCategory() {
		return parentProductCategory;
	}
	public void setParentProductCategory(ProductCategory parentProductCategory) {
		this.parentProductCategory = parentProductCategory;
	}
	
	
	
}
