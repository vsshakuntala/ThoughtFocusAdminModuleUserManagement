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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * The persistent class for the BOM database table.
 * 
 * @author Arvind.C
 *
 */
@Entity
public class Bom implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long bomId;
	private Part part;
	private Part parentPart;
	private Catalog catalog;
	private Integer entryId;
	private Integer quantity;
	private Timestamp createdDate;
	private Long createdBy;
	private Timestamp modifiedDate;
	private Long modifiedBy;
	private Boolean active;
	private Set<MetadataValue> metadataValues;
	private Boolean aftermarketItem;

	@Id
	@Column(name="BOM_ID")
	@GeneratedValue
	public Long getBomId() {
		return bomId;
	}
	public void setBomId(Long bomId) {
		this.bomId = bomId;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PART_ID")
	public Part getPart() {
		return part;
	}
	public void setPart(Part part) {
		this.part = part;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PARENT_PART_ID")
	public Part getParentPart() {
		return parentPart;
	}
	public void setParentPart(Part parentPart) {
		this.parentPart = parentPart;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CATALOG_ID")
	public Catalog getCatalog() {
		return catalog;
	}
	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}
	
	@Column(name="ENTRY_ID")
	public Integer getEntryId() {
		return entryId;
	}
	public void setEntryId(Integer entryId) {
		this.entryId = entryId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Column(name="CREATED_DATE")
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	
	@Column(name="CREATED_BY")
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	
	@Column(name="MODIFIED_DATE")
	public Timestamp getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	@Column(name="MODIFIED_BY")
	public Long getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="bom")
	public Set<MetadataValue> getMetadataValues() {
		return metadataValues;
	}
	public void setMetadataValues(Set<MetadataValue> metadataValues) {
		this.metadataValues = metadataValues;
	}

	public void setAftermarketItem(Boolean aftermarketItem) {
		this.aftermarketItem = aftermarketItem;
	}
	
	@Column(name="AFTERMARKET_ITEM")
	public Boolean getAftermarketItem() {
		return aftermarketItem;
	}
	@Override
	public String toString() {
		return "Bom [bomId=" + bomId + ", active=" + active + "]";
	}

}
