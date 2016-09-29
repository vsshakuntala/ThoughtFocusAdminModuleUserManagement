package com.tf.usermanagement.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * The persistent class for the GROUP_HIERARCHY database table.
 * 
 * @author Manaswita Mishra
 * 
 */
@Entity
@Table(name="GROUP_HIERARCHY")
public class GroupHierarchy implements Serializable, IAuditLog{

	private static final long serialVersionUID = 1L;

	private GroupHierarchyPK compositeId;
	private Boolean rollUpData;
	private Timestamp createdDate;
	private Long createdBy;
	private Timestamp modifiedDate;
	private Long modifiedBy;
	private Boolean active;
	

	@EmbeddedId
	public GroupHierarchyPK getCompositeId() {
		return compositeId;
	}

	public void setCompositeId(GroupHierarchyPK compositeId) {
		this.compositeId = compositeId;
	}
	
	public void setRollUpData(Boolean rollUpData) {
		this.rollUpData = rollUpData;
	}
	
	@Column(name="ROLL_UP_DATA")
	public Boolean getRollUpData() {
		return rollUpData;
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
	
	@Column(name="ACTIVE")
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	
	@Transient
	@Override
	public Long getId() {
		return this.compositeId.getGroupId();
	}

	@Transient
	@Override
	public String getLogDetail() {
		StringBuilder sb = new StringBuilder();
		sb.append(" Group Id : ").append(compositeId.getGroupId())
		.append(" Group Name : ").append(compositeId.getParentGroupId());
		
		//logger.debug("*************************************************"+sb);
 
		return sb.toString();
	}

	@Transient
	@Override
	public Serializable getCompositeKey() {
		return this.getCompositeId();
	}
	
}

