package com.tf.usermanagement.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the CATALOG_MEDIA database table.
 * 
 * @author Arvind Rao
 * 
 */
@Embeddable
public class CatalogMediaPK implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long catalogId;
	private Long mediaId;
	private Long partId;

	public CatalogMediaPK() {
	}

	@Column(name = "CATALOG_ID")
	public Long getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(Long catalogId) {
		this.catalogId = catalogId;
	}
	@Column(name = "MEDIA_ID")
	public Long getMediaId() {
		return mediaId;
	}

	public void setMediaId(Long mediaId) {
		this.mediaId = mediaId;
	}
	@Column(name = "PART_ID")
	public Long getPartId() {
		return partId;
	}

	public void setPartId(Long partId) {
		this.partId = partId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CatalogMediaPK)) {
			return false;
		}
		CatalogMediaPK castOther = (CatalogMediaPK) other;
		return (this.catalogId.equals(castOther.catalogId))
				&& (this.mediaId.equals(castOther.mediaId))
				&& (this.partId.equals(castOther.partId));

	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.catalogId ^ (this.catalogId >>> 32)));
		hash = hash * prime	+ ((int) (this.mediaId ^ (this.mediaId >>> 32)));
		hash = hash * prime	+ ((int) (this.partId ^ (this.partId >>> 32)));
		return hash;
	}
	
}