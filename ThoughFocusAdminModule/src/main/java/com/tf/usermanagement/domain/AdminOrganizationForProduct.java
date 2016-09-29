package com.tf.usermanagement.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the ORGANIZATION database table.
 * 
 * @author Manaswita Mishra
 * 
 */
@Entity
@Table(name="ORGANIZATION")
public class AdminOrganizationForProduct implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer organizationId;
	private String organizationName;
	private String description;
	private Long mediaId;
	private Set<AdminProductCatalogOrganization> adminProductCatalogOrganization;
	@Id
	@Column(name = "ORGANIZATION_ID")
	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "ORGANIZATION_NAME")
	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

/*	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="ORGANIZATION_ID",insertable=false,updatable=false)
	public UserOrgBillToShip getUserOrgBillToShip() {
		return userOrgBillToShip;
	}

	public void setUserOrgBillToShip(UserOrgBillToShip userOrgBillToShip) {
		this.userOrgBillToShip = userOrgBillToShip;
	}*/

	
/*	@OneToMany(fetch=FetchType.LAZY, mappedBy = "organization")
	public Set<OrgIntermediaryMap> getOrgIntermediaryMap() {
		return orgIntermediaryMap;
	}

	public void setOrgIntermediaryMap(Set<OrgIntermediaryMap> orgIntermediaryMap) {
		this.orgIntermediaryMap = orgIntermediaryMap;
	}*/

	@Column(name="MEDIA_ID")
	public Long getMediaId() {
		return mediaId;
	}

	public void setMediaId(Long mediaId) {
		this.mediaId = mediaId;
	}
	
	@Override
	public int hashCode(){
		return this.organizationId.hashCode();
	}
	
	@OneToMany(mappedBy = "organization", fetch = FetchType.LAZY)
	public Set<AdminProductCatalogOrganization> getAdminProductCatalogOrganization() {
		return adminProductCatalogOrganization;
	}

	public void setAdminProductCatalogOrganization(Set<AdminProductCatalogOrganization> adminProductCatalogOrganization) {
		this.adminProductCatalogOrganization = adminProductCatalogOrganization;
	}


	
	@Override
	public boolean equals(Object obj){
		boolean result=false;
		if (obj instanceof AdminOrganizationForProduct){
			AdminOrganizationForProduct o = (AdminOrganizationForProduct)obj;
			result = (o.getOrganizationId().equals(this.organizationId));
		}
		return result;
	}

	@Override
	public String toString() {
		return "Organization [organizationId=" + organizationId 
				+ ", organizationName=" + organizationName + ", description="
				+ description + "]";
	}
}
