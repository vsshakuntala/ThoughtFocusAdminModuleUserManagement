package com.tf.usermanagement.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * The persistent class for the USER_ACTION_METADATA_VALUE database table.
 * 
 * @author Manaswita Mishra
 * 
 */
@Entity
@Table(name="USER_ACTION_METADATA_VALUE")
public class UserActionMetadataValue implements Serializable{

	private static final long serialVersionUID = 1L;

	private UserActionMetadataValuePK compositeId;
	private String value;
	private Timestamp createdDate;
	private Long createdBy;
	private Timestamp modifiedDate;
	private Long modifiedBy;
	private UserAction userAction;

	@EmbeddedId
	public UserActionMetadataValuePK getCompositeId() {
		return compositeId;
	}

	public void setCompositeId(UserActionMetadataValuePK compositeId) {
		this.compositeId = compositeId;
	}


	@Column(name = "VALUE")
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Column(name = "CREATED_DATE")
	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "CREATED_BY")
	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "MODIFIED_DATE")
	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Column(name = "MODIFIED_BY")
	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@ManyToOne
	@JoinColumn(name = "USER_ACTION_ID", insertable=false, updatable=false)
	public UserAction getUserAction() {
		return userAction;
	}

	public void setUserAction(UserAction userAction) {
		this.userAction = userAction;
	}
	
}
