package com.tf.usermanagement.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DOCUMENT_FOLDER_ORG_MAP")
public class DocumentOrganizationMap implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private DocumentFolderOrganizationPK id;
	private Long createdBy;
	private Timestamp createdDate;
	private Long modifiedBy;
	private Timestamp modifiedDate;
	private Boolean active;
	 
	private DocumentFolder documentFolder;
	private Organization organization;
	
	@EmbeddedId
	public DocumentFolderOrganizationPK getId() {
		return id;
	}
	public void setId(DocumentFolderOrganizationPK id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name="DOCUMENT_FOLDER_ID" , insertable=false,updatable=false)
	public DocumentFolder getDocumentFolder() {
		return documentFolder;
	}
	public void setDocumentFolder(DocumentFolder documentFolder) {
		this.documentFolder = documentFolder;
	}
	
	
	@OneToOne
	@JoinColumn(name="ORGANIZATION_ID" , insertable=false,updatable=false)
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
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
	
	@Column(name = "ACTIVE")
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	

	
	
}
