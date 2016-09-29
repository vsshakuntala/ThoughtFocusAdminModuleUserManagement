package com.tf.usermanagement.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

/**
 * The persistent class for the CATALOG_MEDIA database table.
 * 
 * @author Arvind Rao
 * 
 */
@Entity
@Table(name = "CATALOG_MEDIA")
public class CatalogMedia implements Serializable {
	private static final long serialVersionUID = 1L;
	private CatalogMediaPK id;
	private Boolean active;
	private Catalog catalog;
	private Media media;
	private Long createdBy;
	private Timestamp createdDate;
	private Long modifiedBy;
	private Timestamp modifiedDate;
	//Added by Rajesh 
	//private Part part;
	

	public CatalogMedia() {
	}

	@EmbeddedId
	public CatalogMediaPK getId() {
		return this.id;
	}

	public void setId(CatalogMediaPK id) {
		this.id = id;
	}

	@Column(name = "ACTIVE")
	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
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

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "CATALOG_ID", insertable=false, updatable=false)
	public Catalog getCatalog() {
		return catalog;
	}

	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}

	@ManyToOne
	@JoinColumn(name = "MEDIA_ID", insertable=false, updatable=false)
	public Media getMedia() {
		return media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}
	
	//Added by Rajesh 
	/*@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PART_ID", insertable=false, updatable=false)
	public Part getPart() {
		return part;
	}

	public void setPart(Part part) {
		this.part = part;
	}*/
	
}