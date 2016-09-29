package com.tf.usermanagement.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the ORDERABLE_PART database table.
 * 
 */
@Entity
@Table(name="ORDERABLE_PART")
public class OrderablePart implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private OrderablePartPK id;
	private Boolean active;
	private Long createdBy;
	private Date createdDate;
	private Long modifiedBy;
	private Date modifiedDate;
	private Part part;
	private Part orderablePart;
	private Integer substituteTypeId;

    public OrderablePart() {
    }

	public void setId(OrderablePartPK id) {
		this.id = id;
	}
	
	@EmbeddedId
	public OrderablePartPK getId() {
		return id;
	}

	@Column(name="ACTIVE")
	public Boolean getActive() {
		return active;
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
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
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
	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public void setPart(Part part) {
		this.part = part;
	}
	
	@ManyToOne
	@JoinColumn(name="PART_ID", insertable=false, updatable=false)
	public Part getPart() {
		return part;
	}

	public void setOrderablePart(Part orderablePart) {
		this.orderablePart = orderablePart;
	}

	@ManyToOne
	@JoinColumn(name="ORDERABLE_PART_ID", insertable=false, updatable=false)
	public Part getOrderablePart() {
		return orderablePart;
	}

	public void setSubstituteTypeId(Integer substituteTypeId) {
		this.substituteTypeId = substituteTypeId;
	}

	@Column(name="SUBSTITUTE_TYPE_ID")
	public Integer getSubstituteTypeId() {
		return substituteTypeId;
	}



}