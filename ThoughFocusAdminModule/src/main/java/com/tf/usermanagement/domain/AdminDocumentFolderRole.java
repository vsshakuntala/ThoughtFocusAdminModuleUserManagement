package com.tf.usermanagement.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.*;

/**
 * The persistent class for the DOCUMENT_FOLDER_ROLE database table.
 * 
 * @author Arvind.C
 * 
 */
@Entity
@Table(name = "DOCUMENT_FOLDER_ROLE")
public class AdminDocumentFolderRole implements Serializable {
	private static final long serialVersionUID = 1L;
	private long documentFolderRoleId;
	private AdminDocumentFolder documentFolder;
	private Role roleId;
	private Boolean active = true;
	private Long createdBy;
	private Timestamp createdDate;
	private Long modifiedBy;
	private Timestamp modifiedDate;

	public AdminDocumentFolderRole() {
	}

	@Id
	@Column(name="DOCUMENT_FOLDER_ROLE_ID")
	@GeneratedValue
	public long getDocumentFolderRoleId() {
		return documentFolderRoleId;
	}


	public void setDocumentFolderRoleId(long documentFolderRoleId) {
		this.documentFolderRoleId = documentFolderRoleId;
	}

	@OneToOne
	@JoinColumn(name="DOCUMENT_FOLDER_ID" , insertable=true,updatable=false)
	public AdminDocumentFolder getDocumentFolder() {
		return documentFolder;
	}

	public void setDocumentFolder(AdminDocumentFolder documentFolder) {
		this.documentFolder = documentFolder;
	}

	@ManyToOne
	@JoinColumn(name = "ROLE_ID", insertable=true, updatable=false)
	public Role getRoleId() {
		return roleId;
	}

	public void setRoleId(Role roleId) {
		this.roleId = roleId;
	}


	@Column(name = "ACTIVE")
	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
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

}