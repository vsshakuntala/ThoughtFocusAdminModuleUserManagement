package com.tf.usermanagement.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;

/**
 * The persistent class for the PART_HISTORY database table.
 * 
 */
@Entity
@Table(name = "PART_HISTORY")
public class PartHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	private PartHistoryPK id;
	private Part part;
	private Part substitutePart;
	private Long createdBy;
	private Date createdDate;
	private Date effectiveFromDate;
	private Date effectiveToDate;
	private String instructions;
	private Long modifiedBy;
	private Date modifiedDate;
	private Boolean active;

	public PartHistory() {
	}

	public void setId(PartHistoryPK id) {
		this.id = id;
	}

	@EmbeddedId
	public PartHistoryPK getId() {
		return id;
	}

	@ManyToOne
	@JoinColumn(name = "PART_ID", insertable = false, updatable = false)
	public Part getPart() {
		return part;
	}

	public void setPart(Part part) {
		this.part = part;
	}

	@ManyToOne
	@JoinColumn(name = "SUBSTITUTE_PART_ID", insertable = false, updatable = false)
	public Part getSubstitutePart() {
		return substitutePart;
	}

	public void setSubstitutePart(Part substitutePart) {
		this.substitutePart = substitutePart;
	}

	@Column(name = "CREATED_BY")
	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "CREATED_DATE")
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "EFFECTIVE_TO_DATE")
	public Date getEffectiveToDate() {
		return effectiveToDate;
	}

	public void setEffectiveToDate(Date effectiveToDate) {
		this.effectiveToDate = effectiveToDate;
	}

	@Column(name = "INSTRUCTIONS")
	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	@Column(name = "MODIFIED_BY")
	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@Column(name = "MODIFIED_DATE")
	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Column(name = "ACTIVE", insertable = false, updatable = false)
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public void setEffectiveFromDate(Date effectiveFromDate) {
		this.effectiveFromDate = effectiveFromDate;
	}

	@Column(name = "EFFECTIVE_FROM_DATE", insertable = false, updatable = false)
	public Date getEffectiveFromDate() {
		return effectiveFromDate;
	}

}