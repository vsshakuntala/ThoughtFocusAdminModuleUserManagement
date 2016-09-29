package com.tf.usermanagement.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DocumentFolderOrganizationPK implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long documentFolderId;
	private Long organizationId;
	
	@Column(name="DOCUMENT_FOLDER_ID")
	public Long getDocumentFolderId() {
		return documentFolderId;
	}
	public void setDocumentFolderId(Long documentFolderId) {
		this.documentFolderId = documentFolderId;
	}
	
	@Column(name="ORGANIZATION_ID")
	public Long getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}
	
	
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DocumentFolderOrganizationPK)) {
			return false;
		}
		DocumentFolderOrganizationPK castOther = (DocumentFolderOrganizationPK)other;
		return 
			(this.documentFolderId == castOther.documentFolderId)
			&& (this.organizationId == castOther.organizationId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.documentFolderId ^ (this.documentFolderId >>> 32)));
		hash = hash * prime + ((int) (this.organizationId ^ (this.organizationId >>> 32)));
		
		return hash;
    }
	
}
