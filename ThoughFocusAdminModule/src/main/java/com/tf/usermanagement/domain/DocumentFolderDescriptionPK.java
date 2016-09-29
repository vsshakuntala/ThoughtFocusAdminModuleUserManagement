package com.tf.usermanagement.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DocumentFolderDescriptionPK implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long documentFolderId;
	private Long languageId;
	
	@Column(name="DOCUMENT_FOLDER_ID")
	public Long getDocumentFolderId() {
		return documentFolderId;
	}
	public void setDocumentFolderId(Long documentFolderId) {
		this.documentFolderId = documentFolderId;
	}	
	@Column(name="LANGUAGE_ID")
	public Long getLanguageId() {
		return languageId;
	}
	public void setLanguageId(Long languageId) {
		this.languageId = languageId;
	}
	
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DocumentFolderDescriptionPK)) {
			return false;
		}
		DocumentFolderDescriptionPK castOther = (DocumentFolderDescriptionPK) other;
		return (this.documentFolderId.equals(castOther.documentFolderId))
				&& (this.languageId.equals(castOther.languageId));

	}
	
	
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.documentFolderId ^ (this.documentFolderId >>> 32)));
		hash = hash * prime
				+ ((int) (this.languageId ^ (this.languageId >>> 32)));

		return hash;
	}
	
	

	@Override
	public String toString() {
		return "DocumentFolderDescriptionPK [documentFolderId=" + documentFolderId + ", languageId=" + languageId + "]";
	}

	
}
