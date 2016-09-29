package com.tf.usermanagement.domain;

import java.io.Serializable;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * The persistent class for the ProductCatalog database table.
 * 
 * @author Ganeshan.K
 * 
 */

@Entity
@Table(name = "PRODUCT_CATALOG")
public class AdminProductCatalog implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long productCatalogId;
	private Media media;
	private Long createdBy;
	private Timestamp createdDate;
	private Long modifiedBy;
	private Timestamp modifiedDate;
	private Boolean active;
	private Boolean isProductFamily;
	
	private AdminProductCatalogDescription productCatalogDescription;
	private Set<AdminProductCategoryHierarchy> productCategoryHierarchy;
	private Set<ProductCategoryPart> productCategoryPart;
	private Set<AdminProductCatalogOrganization> productCatalogOrganization;
	
	
	@Id
	@Column(name="PRODUCT_CATALOG_ID")
/*	@SequenceGenerator(name = "ProductCatalogSequence", sequenceName = "SEQ_PRODUCTCATALOG")
	@GeneratedValue(generator="ProductCatalogSequence")*/
	@GeneratedValue
	public Long getProductCatalogId() {
		return productCatalogId;
	}
	public void setProductCatalogId(Long productCatalogId) {
		this.productCatalogId = productCatalogId;
	}	
	
	@OneToOne
	@JoinColumn(name="MEDIA_ID")
	public Media getMedia() {
		return media;
	}
	public void setMedia(Media media) {
		this.media = media;
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
	
	@Column(name="IS_PRODUCT_FAMILY")
	public Boolean getIsProductFamily() {
		return isProductFamily;
	}
	public void setIsProductFamily(Boolean isProductFamily) {
		this.isProductFamily = isProductFamily;
	}

	
	@OneToOne(mappedBy = "productCatalog", fetch = FetchType.LAZY)
	public AdminProductCatalogDescription getProductCatalogDescription() {
		return productCatalogDescription;
	}
	public void setProductCatalogDescription(
			AdminProductCatalogDescription productCatalogDescription) {
		this.productCatalogDescription = productCatalogDescription;
	}
	@OneToMany(mappedBy = "productCatalog")
	@LazyCollection(LazyCollectionOption.TRUE)
	public Set<AdminProductCategoryHierarchy> getProductCategoryHierarchy() {
		return productCategoryHierarchy;
	}
	public void setProductCategoryHierarchy(
			Set<AdminProductCategoryHierarchy> productCategoryHierarchy) {
		this.productCategoryHierarchy = productCategoryHierarchy;
	}
	
	@OneToMany(mappedBy = "productCatalog", fetch = FetchType.LAZY)
	public Set<ProductCategoryPart> getProductCategoryPart() {
		return productCategoryPart;
	}
	public void setProductCategoryPart(Set<ProductCategoryPart> productCategoryPart) {
		this.productCategoryPart = productCategoryPart;
	}
	
	@OneToMany(mappedBy = "productCatalog", fetch = FetchType.LAZY)
	public Set<AdminProductCatalogOrganization> getProductCatalogOrganization() {
		return productCatalogOrganization;
	}
	public void setProductCatalogOrganization(
			Set<AdminProductCatalogOrganization> productCatalogOrganization) {
		this.productCatalogOrganization = productCatalogOrganization;
	}
	
	
	@Override
	public int hashCode(){
		return this.productCatalogId.hashCode();
	}
	
	
	
	@Override
	public boolean equals(Object obj){
		boolean result=false;
		if (obj instanceof AdminProductCatalog){
			AdminProductCatalog cat=(AdminProductCatalog)obj;
			result = (cat.getProductCatalogId().equals(this.productCatalogId));
		}
		return result;
	}
	@Override
	public String toString() {
		return "AdminProductCatalog [productCatalogId=" + productCatalogId
				+ ", media=" + media + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", modifiedBy=" + modifiedBy
				+ ", modifiedDate=" + modifiedDate + ", active=" + active
				+ ", isProductFamily=" + isProductFamily
				+ "]";
	}
	
	/*@Override
	public String toString(){
		return "Product Catalog [Product_Catalog_Id=" + productCatalogId + ","
				+ ", active=" + active + "]";
	}*/
	
	
}
