package com.tf.usermanagement.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

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

/**
 * The persistent class for the DocumentFolder database table.
 * 
 * 
 */

@Entity
@Table(name = "DOCUMENT_FOLDER")
public class DocumentFolder implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long documentFolderId;;
	private Long createdBy;
	private Timestamp createdDate;
	private Long modifiedBy;
	private Timestamp modifiedDate;
	private Boolean active;
	
	private Organization organization;
	
	private Set<DocumentFolderHierarchy> documentFolderHierarchy;
	
	private DocumentFolderDescription documentFolderDescription;
//	private DocumentOrganizationMap documentOrganization;
	private Set<DocumentFolder> documentFolder;
	
	private List<DocumentFolderRole> documentFolderRole;
	
	private Set<DocumentFolderMedia> documentFolderMedia; 

	
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
	
	
	@OneToOne(mappedBy = "documentFolder", fetch = FetchType.LAZY)
	public DocumentFolderDescription getDocumentFolderDescription() {
		return documentFolderDescription;
	}
	public void setDocumentFolderDescription(DocumentFolderDescription documentFolderDescription) {
		this.documentFolderDescription = documentFolderDescription;
	}
	
	@OneToMany(mappedBy = "documentFolder", fetch = FetchType.LAZY)
	public Set<DocumentFolderHierarchy> getDocumentFolderHierarchy() {
		return documentFolderHierarchy;
	}
	public void setDocumentFolderHierarchy(Set<DocumentFolderHierarchy> documentFolderHierarchy) {
		this.documentFolderHierarchy = documentFolderHierarchy;
	}
	
	@OneToMany(mappedBy = "documentFolder", fetch = FetchType.LAZY)
	public Set<DocumentFolder> getDocumentFolder() {
		return documentFolder;
	}
	public void setDocumentFolder(Set<DocumentFolder> documentFolder) {
		this.documentFolder = documentFolder;
	}
	
	@Override
	public int hashCode(){
		return this.documentFolderId.hashCode();
	}

	@ManyToOne
	@JoinColumn(name="ORGANIZATION_ID" , insertable=false,updatable=false)
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	
	@OneToMany(mappedBy = "documentFolder", fetch = FetchType.LAZY)
	public List<DocumentFolderRole> getDocumentFolderRole() {
		return documentFolderRole;
	}
	public void setDocumentFolderRole(List<DocumentFolderRole> documentFolderRole) {
		this.documentFolderRole = documentFolderRole;
	}
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "documentFolder")
	public Set<DocumentFolderMedia> getDocumentFolderMedia() {
		return documentFolderMedia;
	}
	public void setDocumentFolderMedia(Set<DocumentFolderMedia> documentFolderMedia) {
		this.documentFolderMedia = documentFolderMedia;
	}
	
}
