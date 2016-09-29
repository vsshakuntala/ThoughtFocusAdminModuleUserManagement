package com.tf.usermanagement.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USER_ORG_INTERMEDIARY_MAP")
public class UserOrgIntermediaryMap implements Serializable{
	
 
	private static final long serialVersionUID = 1L;
	

	private UserOrgIntermediaryMapPK id;


	private Long createdBy;
	private Date createdDate;
	private Long modifiedBy;
	private Date modifiedDate;
	private UserOrganizationMap userOrg;
	
	@Column(name="ACTIVE")
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	private Intermediary intermediary;
    private Boolean active = Boolean.TRUE;
 	public UserOrgIntermediaryMap() {
	super();
 		}
 
	public UserOrgIntermediaryMap(UserOrgIntermediaryMapPK id, Long createdBy,
			Date createdDate, Long modifiedBy, Date modifiedDate,
			Long userOrgMapId) {
			super();
			this.createdBy = createdBy;
			this.createdDate = createdDate;
			this.modifiedBy = modifiedBy;	
			this.modifiedDate = modifiedDate;
		}

	@EmbeddedId
	public UserOrgIntermediaryMapPK getId() {
		return id;
	}

	public void setId(UserOrgIntermediaryMapPK id) {
		this.id = id;
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

	@ManyToOne
	@JoinColumn(name = "USER_ORG_ID" ,insertable = false , updatable=false)
	public UserOrganizationMap getUserOrg() {
		return userOrg;
	}

	public void setUserOrg(UserOrganizationMap userOrg) {
		this.userOrg = userOrg;
	}
	@OneToOne
	@JoinColumn(name = "INTERMEDIARY_ID", insertable = false , updatable=false)
	public Intermediary getIntermediary() {
		return intermediary;
	}

	public void setIntermediary(Intermediary intermediary) {
		this.intermediary = intermediary;
	}
	
}
