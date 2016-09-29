package com.tf.usermanagement.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MachineCatalogPK implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long userId;
	private Long catalogid;

	public MachineCatalogPK() {
	}

	@Column(name = "USER_ID")
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "CATALOG_ID")
	public Long getCatalogid() {
		return catalogid;
	}

	public void setCatalogid(Long catalogid) {
		this.catalogid = catalogid;
	}

	
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.userId ^ (this.userId >>> 32)));
		hash = hash * prime
				+ ((int) (this.catalogid ^ (this.catalogid >>> 32)));

		return hash;
	}

	@Override
	public String toString() {
		return "PartDescriptionPK [userId=" + userId + ", catalogid=" + catalogid + "]";
	}
	
	
}
