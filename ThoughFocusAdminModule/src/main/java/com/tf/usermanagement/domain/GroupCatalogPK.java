package com.tf.usermanagement.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The composite key class for the GROUP_CATALOG database table.
 * 
 * @author Manaswita Mishra
 * 
 */
@Embeddable
public class GroupCatalogPK implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long groupId;
	private Long catalogId;

	public GroupCatalogPK() {
	}

	@Column(name = "GROUP_ID")
	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	@Column(name = "CATALOG_ID")
	public Long getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(Long catalogId) {
		this.catalogId = catalogId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof GroupHierarchyPK)) {
			return false;
		}
		GroupCatalogPK castOther = (GroupCatalogPK) other;
		return (this.groupId.equals(castOther.groupId))
				&& (this.catalogId.equals(castOther.catalogId));

	}
	
	
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.groupId ^ (this.groupId >>> 32)));
		hash = hash * prime
				+ ((int) (this.catalogId ^ (this.catalogId >>> 32)));

		return hash;
	}

	@Override
	public String toString() {
		return "GroupCatalogPK [groupId=" + groupId + ", catalogId=" + catalogId + "]";
	}
	
}