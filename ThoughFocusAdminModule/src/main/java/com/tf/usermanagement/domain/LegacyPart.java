package com.tf.usermanagement.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The persistent class for the LEGACY_PART database table.
 * 
 * @author Arvind Rao
 * 
 */
@Entity
@Table(name = "LEGACY_PART")
public class LegacyPart implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private LegacyPartPK id;
	private Part part;
	private String legacyPartNumber;
	private String documentPartNumber;
	private Boolean primaryPart;
	private Timestamp effectiveFromDate;
	private Timestamp effectiveToDate;
	private Long createdBy;
	private Timestamp createdDate;
	private Long modifiedBy;
	private Timestamp modifiedDate;
	private Boolean active;
	private String description;
	

	@EmbeddedId
	public LegacyPartPK getId() {
		return id;
	}
	public void setId(LegacyPartPK id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name="PART_ID", insertable=false, updatable=false)
	public Part getPart() {
		return part;
	}

	public void setPart(Part part) {
		this.part = part;
	}

	@Column(name = "LEGACY_PART_NUMBER")
	public String getLegacyPartNumber() {
		return legacyPartNumber;
	}
	public void setLegacyPartNumber(String legacyPartNumber) {
		this.legacyPartNumber = legacyPartNumber;
	}
	public void setDocumentPartNumber(String documentPartNumber) {
		this.documentPartNumber = documentPartNumber;
	}
	@Column(name = "DOCUMENT_PART_NUMBER")
	public String getDocumentPartNumber() {
		return documentPartNumber;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}
	@Column(name = "PRIMARY_PART")
	public Boolean getPrimaryPart() {
		return primaryPart;
	}
	public void setPrimaryPart(Boolean primaryPart) {
		this.primaryPart = primaryPart;
	}

	@Column(name = "EFFECTIVE_FROM_DATE")
	public Timestamp getEffectiveFromDate() {
		return effectiveFromDate;
	}
	public void setEffectiveFromDate(Timestamp effectiveFromDate) {
		this.effectiveFromDate = effectiveFromDate;
	}

	@Column(name = "EFFECTIVE_TO_DATE")
	public Timestamp getEffectiveToDate() {
		return effectiveToDate;
	}
	public void setEffectiveToDate(Timestamp effectiveToDate) {
		this.effectiveToDate = effectiveToDate;
	}

	@Column(name = "CREATED_BY")
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	@Column(name = "CREATED_DATE")
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	@Column(name = "MODIFIED_BY")
	public Long getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	@Column(name = "MODIFIED_DATE")
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
}
