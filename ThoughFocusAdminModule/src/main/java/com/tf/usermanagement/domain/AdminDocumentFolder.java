
package com.tf.usermanagement.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
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

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * The persistent class for the DocumentFolder database table.
 * 
 * 
 */

@Entity
@Table(name = "DOCUMENT_FOLDER")
public class AdminDocumentFolder implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long documentFolderId;;
	private Long createdBy;
	private Timestamp createdDate;
	private Long modifiedBy;
	private Timestamp modifiedDate;
	private Boolean active;
	
	private AdminOrganization organization;
	private int orgId;
	
	private Set<AdminDocumentFolderHierarchy> documentFolderHierarchy;
	
	private AdminDocumentFolderDescription documentFolderDescription;
//	private DocumentOrganizationMap documentOrganization;
	private Set<AdminDocumentFolder> documentFolder;
	
	private List<AdminDocumentFolderRole> documentFolderRole;
	
	private Set<AdminDocumentFolderMedia> documentFolderMedia; 

	
	@Id
	@Column(name="DOCUMENT_FOLDER_ID")
	@GeneratedValue
	public Long getDocumentFolderId() {
		return documentFolderId;
	}
	public void setDocumentFolderId(Long documentFolderId) {
		this.documentFolderId = documentFolderId;
	}
	
	@Column(name="CREATED_BY")
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	
	@Column(name="CREATED_DATE")
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
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
	public Timestamp getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	@Column(name="ACTIVE")
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	
	
	@OneToOne(mappedBy = "documentFolder")
	@LazyCollection(LazyCollectionOption.FALSE)
	public AdminDocumentFolderDescription getDocumentFolderDescription() {
		return documentFolderDescription;
	}
	public void setDocumentFolderDescription(AdminDocumentFolderDescription documentFolderDescription) {
		this.documentFolderDescription = documentFolderDescription;
	}
	
	@OneToMany(mappedBy = "documentFolder", cascade={CascadeType.ALL})
	@LazyCollection(LazyCollectionOption.FALSE)
	public Set<AdminDocumentFolderHierarchy> getDocumentFolderHierarchy() {
		return documentFolderHierarchy;
	}
	public void setDocumentFolderHierarchy(Set<AdminDocumentFolderHierarchy> documentFolderHierarchy) {
		this.documentFolderHierarchy = documentFolderHierarchy;
	}
	
	@OneToMany(mappedBy = "documentFolder", cascade={CascadeType.ALL})
	@LazyCollection(LazyCollectionOption.FALSE)
	public Set<AdminDocumentFolder> getDocumentFolder() {
		return documentFolder;
	}
	public void setDocumentFolder(Set<AdminDocumentFolder> documentFolder) {
		this.documentFolder = documentFolder;
	}
	
	@Override
	public int hashCode(){
		return this.documentFolderId.hashCode();
	}

	@ManyToOne
	@JoinColumn(name="ORGANIZATION_ID" , insertable=false,updatable=false,nullable=false)
	public AdminOrganization getOrganization() {
		return organization;
	}
	public void setOrganization(AdminOrganization organization) {
		this.organization = organization;
	}
	
	@OneToMany(mappedBy = "documentFolder", fetch = FetchType.LAZY,cascade={CascadeType.ALL})
	public List<AdminDocumentFolderRole> getDocumentFolderRole() {
		return documentFolderRole;
	}
	public void setDocumentFolderRole(List<AdminDocumentFolderRole> documentFolderRole) {
		this.documentFolderRole = documentFolderRole;
	}
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "documentFolder",cascade={CascadeType.ALL})
	public Set<AdminDocumentFolderMedia> getDocumentFolderMedia() {
		return documentFolderMedia;
	}
	public void setDocumentFolderMedia(Set<AdminDocumentFolderMedia> documentFolderMedia) {
		this.documentFolderMedia = documentFolderMedia;
	}
	@Column(name = "ORGANIZATION_ID")
	public int getOrgId() {
		return orgId;
	}
	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}
	
}
