package com.tf.usermanagement.domain;

import java.io.Serializable;
import java.sql.Timestamp;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the DocumentFolder database table.
 * 
 * 
 */

@Entity
@Table(name = "DOCUMENT_FOLDER_MEDIA")
public class AdminDocumentFolderMedia implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long documentFolderMediaId;;
	private AdminDocumentFolder documentFolder;
	private Media media;
	private Long createdBy;
	private Timestamp createdDate;
	private Long modifiedBy;
	private Timestamp modifiedDate;
	private Boolean active;
	
	private Long mediaId,documentFolderId;
	
	
	
	@Id
	@Column(name="DOCUMENT_FOLDER_MEDIA_ID")
	@GeneratedValue
	public Long getDocumentFolderMediaId() {
		return documentFolderMediaId;
	}
	public void setDocumentFolderMediaId(Long documentFolderMediaId) {
		this.documentFolderMediaId = documentFolderMediaId;
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
	@JoinColumn(name = "DOCUMENT_FOLDER_ID", insertable=false, updatable=false)
	public AdminDocumentFolder getDocumentFolder() {
		return documentFolder;
	}
	public void setDocumentFolder(AdminDocumentFolder documentFolder) {
		this.documentFolder = documentFolder;
	}
	
	@ManyToOne
	@JoinColumn(name = "MEDIA_ID", insertable=false, updatable=false)
	public Media getMedia() {
		return media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}
	@Column(name = "MEDIA_ID")
	public Long getMediaId() {
		return mediaId;
	}
	public void setMediaId(Long mediaId) {
		this.mediaId = mediaId;
	}
	@Column(name = "DOCUMENT_FOLDER_ID")
	public Long getDocumentFolderId() {
		return documentFolderId;
	}
	public void setDocumentFolderId(Long documentFolderId) {
		this.documentFolderId = documentFolderId;
	}

	
}
