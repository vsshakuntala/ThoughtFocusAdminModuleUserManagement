package com.tf.usermanagement.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class CatalogTreeXmlPK implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	private Long catalogId;
	private Long languageId;

	public CatalogTreeXmlPK() {
	}

	@Column(name = "CATALOG_ID")
	public Long getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(Long catalogId) {
		this.catalogId = catalogId;
	}

	@Column(name = "LANGUAGE_ID")
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
		if (!(other instanceof GroupHierarchyPK)) {
			return false;
		}
		CatalogTreeXmlPK castOther = (CatalogTreeXmlPK) other;
		return (this.catalogId.equals(castOther.catalogId))
				&& (this.languageId.equals(castOther.languageId));

	}
	
	
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.catalogId ^ (this.catalogId >>> 32)));
		hash = hash * prime
				+ ((int) (this.languageId ^ (this.languageId >>> 32)));

		return hash;
	}

	@Override
	public String toString() {
		return "CatalogTreeXmlPK [catalogId=" + catalogId + ", languageId=" + languageId + "]";
	}
	

}
