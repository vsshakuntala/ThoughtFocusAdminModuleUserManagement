package com.tf.usermanagement.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ORG_INTERMEDIARY_MAP")
public class OrgIntermediaryMap implements Serializable{
	
 
	private static final long serialVersionUID = 1L;
	

	private OrgIntermediaryMapPK id;
	private Long createdBy;
	private Date createdDate;
	private Long modifiedBy;
	private Date modifiedDate;
 	private Intermediary intermediary;
 	private Organization organization;
 
 	public OrgIntermediaryMap() {
	super();
 		}
 
	public OrgIntermediaryMap(OrgIntermediaryMapPK id, Long createdBy,
			Intermediary intermediary,Date createdDate, Long modifiedBy, Date modifiedDate,
			Organization organization) {
			super();
			this.organization = organization;
			this.intermediary = intermediary;
			this.createdBy = createdBy;
			this.createdDate = createdDate;
			this.modifiedBy = modifiedBy;	
			this.modifiedDate = modifiedDate;
		}

	@EmbeddedId
	public OrgIntermediaryMapPK getId() {
		return id;
	}

	public void setId(OrgIntermediaryMapPK id) {
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
	@JoinColumn(name="ORGANIZATION_ID" , insertable=false,updatable=false)
	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="ORGANIZATION_ID")
	public Intermediary getIntermediary() {
		return intermediary;
	}

	public void setIntermediary(Intermediary intermediary) {
		this.intermediary = intermediary;
	}


	
}
