package com.tf.usermanagement.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "PRODUCT_CATALOG_ORGANIZATION")
public class AdminProductCatalogOrganization implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private ProductCatalogOrganizationPK id;
	private Long createdBy;
	private Timestamp createdDate;
	private Long modifiedBy;
	private Timestamp modifiedDate;
	private Boolean active;
	 
	private AdminProductCatalog productCatalog;
	private Organization organization;
	
	@EmbeddedId
	public ProductCatalogOrganizationPK getId() {
		return id;
	}
	public void setId(ProductCatalogOrganizationPK id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name="PRODUCT_CATALOG_ID" , insertable=false,updatable=false)
	@LazyCollection(LazyCollectionOption.TRUE)
	public AdminProductCatalog getProductCatalog() {
		return productCatalog;
	}
	public void setProductCatalog(AdminProductCatalog productCatalog) {
		this.productCatalog = productCatalog;
	}
	
	
	@OneToOne
	@JoinColumn(name="ORGANIZATION_ID" , insertable=false,updatable=false)
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
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
	
	@Column(name = "ACTIVE")
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	@Override
	public String toString() {
		return "AdminProductCatalogOrganization [id=" + id + ", createdBy="
				+ createdBy + ", createdDate=" + createdDate + ", modifiedBy="
				+ modifiedBy + ", modifiedDate=" + modifiedDate + ", active="
				+ active + "]";
	}
		
}
