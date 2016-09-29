package com.tf.usermanagement.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USER_ORG_MAP")
public class AdminUserOrganizationMap implements Serializable{
	
 
	private static final long serialVersionUID = 1L;
	

//	private UserOrganizationMapPK id;SEQ_USERORGMAP
	private Long userOrgMapId;
 
	private Boolean approvalStatus;
	private Boolean termsCondition;
	private Long createdBy;
	private Date createdDate;
	private Long modifiedBy;
	private Date modifiedDate;
 	private Long organizationId;
	private AdminUser user;
	private Set<UserOrgIntermediaryMap> userOrgIntermediary;
	private Boolean active = true;
	
	private Organization organization;
 
 	public AdminUserOrganizationMap() {
	super();
 		}
 
	public AdminUserOrganizationMap(Boolean approvalStatus, Boolean termsCondition, Long createdBy,
		Date createdDate, Long modifiedBy, Date modifiedDate) {
			super();
		 

			this.approvalStatus = approvalStatus;
			this.termsCondition = termsCondition;
			this.createdBy = createdBy;
			this.createdDate = createdDate;
			this.modifiedBy = modifiedBy;	
			this.modifiedDate = modifiedDate;
		}

 

	@Column(name = "APPROVAL_STATUS")
	public Boolean getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(Boolean approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	@Column(name = "TERMS_AND_CONDTION")
	public Boolean getTermsCondition() {
		return termsCondition;
	}
	public void setTermsCondition(Boolean termsCondition) {
		this.termsCondition = termsCondition;
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
	
	@Column(name="ORGANIZATION_ID")
 	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="USER_ID")
	public AdminUser getUser() {
		return user;
	}

	public void setUser(AdminUser user) {
		this.user = user;
	}
	@Id
	@Column(name="USER_ORG_ID")
	/*@SequenceGenerator(name = "UserOrgMapSequence", sequenceName = "SEQ_USERORGMAP")
	@GeneratedValue(generator="UserOrgMapSequence")*/
	@GeneratedValue
	public Long getUserOrgMapId() {
		return userOrgMapId;
	}

	public void setUserOrgMapId(Long userOrgMapId) {
		this.userOrgMapId = userOrgMapId;
	}

	/*@OneToOne(fetch=FetchType.LAZY, mappedBy = "organization")*/
	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinColumn(name="USER_ORG_ID")
	public Set<UserOrgIntermediaryMap> getUserOrgIntermediary() {
		return userOrgIntermediary;
	}

	public void setUserOrgIntermediary(Set<UserOrgIntermediaryMap> userOrgIntermediary) {
		this.userOrgIntermediary = userOrgIntermediary;
	}
	
	@Column(name = "ACTIVE")
	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@OneToOne
	@JoinColumn(name="ORGANIZATION_ID",insertable=false,updatable=false)
	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	
	
}
