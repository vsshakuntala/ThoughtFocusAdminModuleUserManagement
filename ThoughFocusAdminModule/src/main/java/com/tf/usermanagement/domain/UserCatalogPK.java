package com.tf.usermanagement.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the USER_CATALOG database table.
 * 
 * @author Arvind.C
 * 
 */
@Embeddable
public class UserCatalogPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long userId;
	private Long catalogId;

	public UserCatalogPK() {
	}

	@Column(name = "USER_ID")
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "CATALOG_ID")
	public Long getCatalogId() {
		return this.catalogId;
	}

	public void setCatalogId(Long catalogId) {
		this.catalogId = catalogId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UserCatalogPK)) {
			return false;
		}
		UserCatalogPK castOther = (UserCatalogPK) other;
		return (this.userId.equals(castOther.userId))
				&& (this.catalogId.equals(castOther.catalogId));

	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.userId ^ (this.userId >>> 32)));
		hash = hash * prime
				+ ((int) (this.catalogId ^ (this.catalogId >>> 32)));

		return hash;
	}

	@Override
	public String toString() {
		return "UserCatalogPK [userId=" + userId + ", catalogId=" + catalogId
				+ "]";
	}
	
	
}