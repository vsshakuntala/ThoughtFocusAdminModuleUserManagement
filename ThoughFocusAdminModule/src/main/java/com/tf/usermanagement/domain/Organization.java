package com.tf.usermanagement.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the ORGANIZATION database table.
 * 
 * @author Narasingha Padhi
 * 
 */
@Entity
@Table(name = "ORGANIZATION")
public class Organization implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer organizationId;
	private String organizationName;
	private String description;
	private Long mediaId;
	private String organizatinReference;
	private Set<OrganizationConfig> organizationConfigs;
	// private UserOrgBillToShip userOrgBillToShip;
	// private Set<OrgIntermediaryMap> orgIntermediaryMap;

	private Set<OrganizationAddress> organizationAddresses;

	private Set<DocumentFolder> documentFolder;

	public Organization() {
	}

	public Organization(Integer organizationId, String organizationName) {
		super();
		this.organizationId = organizationId;
		this.organizationName = organizationName;
	}

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

	/*
	 * @OneToOne(cascade=CascadeType.ALL)
	 * 
	 * @JoinColumn(name="ORGANIZATION_ID",insertable=false,updatable=false)
	 * public UserOrgBillToShip getUserOrgBillToShip() { return
	 * userOrgBillToShip; }
	 * 
	 * public void setUserOrgBillToShip(UserOrgBillToShip userOrgBillToShip) {
	 * this.userOrgBillToShip = userOrgBillToShip; }
	 */

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinColumn(name = "ORGANIZATION_ID")
	public Set<OrganizationConfig> getOrganizationConfigs() {
		return organizationConfigs;
	}

	public void setOrganizationConfigs(
			Set<OrganizationConfig> organizationConfigs) {
		this.organizationConfigs = organizationConfigs;
	}

	/*
	 * @OneToMany(fetch=FetchType.LAZY, mappedBy = "organization") public
	 * Set<OrgIntermediaryMap> getOrgIntermediaryMap() { return
	 * orgIntermediaryMap; }
	 * 
	 * public void setOrgIntermediaryMap(Set<OrgIntermediaryMap>
	 * orgIntermediaryMap) { this.orgIntermediaryMap = orgIntermediaryMap; }
	 */

	@Column(name = "MEDIA_ID")
	public Long getMediaId() {
		return mediaId;
	}

	public void setMediaId(Long mediaId) {
		this.mediaId = mediaId;
	}

	@OneToMany(mappedBy = "organization", fetch = FetchType.LAZY)
	public Set<OrganizationAddress> getOrganizationAddresses() {
		return organizationAddresses;
	}

	public void setOrganizationAddresses(
			Set<OrganizationAddress> organizationAddresses) {
		this.organizationAddresses = organizationAddresses;
	}

	@Override
	public int hashCode() {
		return this.organizationId.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if (obj instanceof Organization) {
			Organization o = (Organization) obj;
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

	@OneToMany(mappedBy = "organization", fetch = FetchType.LAZY)
	public Set<DocumentFolder> getDocumentFolder() {
		return documentFolder;
	}

	public void setDocumentFolder(Set<DocumentFolder> documentFolder) {
		this.documentFolder = documentFolder;
	}

	@Column(name = "ORGANIZATION_REFERENCE")
	public String getOrganizatinReference() {
		return organizatinReference;
	}

	public void setOrganizatinReference(String organizatinReference) {
		this.organizatinReference = organizatinReference;
	}

}
