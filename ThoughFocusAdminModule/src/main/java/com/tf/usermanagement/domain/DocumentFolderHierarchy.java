package com.tf.usermanagement.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "DOCUMENT_FOLDER_HIERARCHY")
public class DocumentFolderHierarchy implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long documentFolderHierarchyId;
	private Long documentFolderId;
	private Long parentDocumentFolderId;
	private Long createdBy;
	private Timestamp createdDate;
	private Long modifiedBy;
	private Timestamp modifiedDate; 
	private Boolean active;
	
	private DocumentFolder documentFolder;
//	private DocumentFolder productCategory;
	private DocumentFolder parentDocumentFolder;
	
	
	@Id
	@Column(name="DOCUMENT_FOLDER_HIERARCHY_ID")
//	@SequenceGenerator(name = "ProductCategoryHierarchySequence", sequenceName = "SEQ_PRODUCTCATEGORYHRCHY")
//	@GeneratedValue(generator="ProductCategoryHierarchySequence")
	@GeneratedValue
	public Long getDocumentFolderHierarchyId() {
		return documentFolderHierarchyId;
	}
	public void setDocumentFolderHierarchyId(Long documentFolderHierarchyId) {
		this.documentFolderHierarchyId = documentFolderHierarchyId;
	}
	
	@Column(name="DOCUMENT_FOLDER_ID", insertable=false, updatable=false)
	public Long getDocumentFolderId() {
		return documentFolderId;
	}
	public void setDocumentFolderId(Long documentFolderId) {
		this.documentFolderId = documentFolderId;
	}
	
	@Column(name="PARENT_DOCUMENT_FOLDER_ID", insertable=false, updatable=false)
	public Long getParentDocumentFolderId() {
		return parentDocumentFolderId;
	}
	public void setParentDocumentFolderId(Long parentDocumentFolderId) {
		this.parentDocumentFolderId = parentDocumentFolderId;
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
	
	@ManyToOne
	@JoinColumn(name = "PARENT_DOCUMENT_FOLDER_ID", insertable=false, updatable=false)
	public DocumentFolder getDocumentFolder() {
		return documentFolder;
	}
	public void setDocumentFolder(DocumentFolder documentFolder) {
		this.documentFolder = documentFolder;
	}
	
	@OneToOne
	@JoinColumn(name = "DOCUMENT_FOLDER_ID", insertable=false, updatable=false)
	public DocumentFolder getParentDocumentFolder() {
		return parentDocumentFolder;
	}
	public void setParentDocumentFolder(DocumentFolder parentDocumentFolder) {
		this.parentDocumentFolder = parentDocumentFolder;
	}
	
	
	
}
